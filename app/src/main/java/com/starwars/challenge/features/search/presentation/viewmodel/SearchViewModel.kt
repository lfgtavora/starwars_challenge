package com.starwars.challenge.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starwars.challenge.features.search.domain.model.Character
import com.starwars.challenge.features.search.domain.usecase.ISearchUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: ISearchUseCase
) : ViewModel() {

    companion object {
        private const val DEBOUNCE_DELAY = 1500L
        private const val DEBOUNCE_MIN_CHAR = 3
    }

    private var searchQuery = ""
    var searchJob: Job? = null
    private val _suggestionList = MutableStateFlow<SuggestionStates>(SuggestionStates.Empty)
    val suggestionList: StateFlow<SuggestionStates>
        get() = _suggestionList

    private val _suggestionList2 = MutableStateFlow<List<Character>>(emptyList())
    val suggestionList2: StateFlow<List<Character>> = _suggestionList2


    sealed class SuggestionStates {
        object Loading : SuggestionStates()
        object Empty : SuggestionStates()
        data class Success(val value: List<Any>) : SuggestionStates()
        data class Error(val message: String) : SuggestionStates()
    }

    fun fetchSearchQuerySuggestions(query: String) {
        _suggestionList.value = SuggestionStates.Loading
        searchJob = viewModelScope.launch {

            if (query == searchQuery || query.length < DEBOUNCE_MIN_CHAR)
                return@launch

            searchQuery = query

            delay(DEBOUNCE_DELAY)

            _suggestionList.value = SuggestionStates.Success(listOf(searchQuery))
        }

        searchJob?.start()
    }

    fun fetchSearchQuerySuggestions2(query: String) {
        if (query == searchQuery || query.length < DEBOUNCE_MIN_CHAR)
            return

        searchQuery = query

        viewModelScope.launch {
            delay(DEBOUNCE_DELAY)

            if (query != searchQuery) return@launch

            searchUseCase.execute(query)
                .collect { res ->
                    _suggestionList2.value = res
                }
        }
    }
}