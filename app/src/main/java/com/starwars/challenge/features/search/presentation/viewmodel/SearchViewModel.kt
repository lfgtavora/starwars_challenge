package com.starwars.challenge.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starwars.challenge.features.search.data.entity.SuggestionSearchResponse
import com.starwars.challenge.features.search.domain.usecase.SearchUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(private val useCase: SearchUseCase) : ViewModel() {

    companion object {
        private const val DEBOUNCE_DELAY = 1500L
        private const val DEBOUNCE_MIN_CHAR = 3
    }

    private var searchQuery = ""
    var searchJob: Job? = null
    private val _suggestionList = MutableStateFlow<SuggestionStates>(SuggestionStates.Empty)
    val suggestionList: StateFlow<SuggestionStates>
        get() = _suggestionList


    sealed class SuggestionStates {
        object Loading : SuggestionStates()
        object Empty : SuggestionStates()
        data class Success(val value: SuggestionSearchResponse) : SuggestionStates()
        data class Error(val message: String) : SuggestionStates()
    }

    fun fetchSearchQuerySuggestions(query: String) {
        _suggestionList.value = SuggestionStates.Loading
        searchJob = viewModelScope.launch {

            if (query == searchQuery || query.length < DEBOUNCE_MIN_CHAR)
                return@launch

            searchQuery = query

            delay(DEBOUNCE_DELAY)

            useCase.fetchSuggestionList(query)
                .collect {
                    _suggestionList.value = SuggestionStates.Success(it!!)
                }


        }

        searchJob?.start()
    }

}