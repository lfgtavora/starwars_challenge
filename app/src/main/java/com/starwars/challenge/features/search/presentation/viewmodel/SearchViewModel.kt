package com.starwars.challenge.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starwars.challenge.features.search.domain.model.CharacterModel
import com.starwars.challenge.features.search.domain.usecase.ISearchUseCase
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

    private val _suggestionList2 = MutableStateFlow<SuggestionStates>(SuggestionStates.Empty)
    val suggestionList2: StateFlow<SuggestionStates> = _suggestionList2


    sealed class SuggestionStates {
        object Loading : SuggestionStates()
        object Empty : SuggestionStates()
        data class Success(val value: List<CharacterModel>) : SuggestionStates()
        data class Error(val message: String) : SuggestionStates()
    }

    fun fetchSearchQuerySuggestions(query: String) {
        if (query == searchQuery || query.length < DEBOUNCE_MIN_CHAR)
            return

        searchQuery = query

        viewModelScope.launch {
            _suggestionList2.value = SuggestionStates.Loading

            delay(DEBOUNCE_DELAY)

            if (query != searchQuery) return@launch

            searchUseCase.execute(query)
                .catch { _suggestionList2.value = SuggestionStates.Error(it.message ?: "Error") }
                .collect { _suggestionList2.value = SuggestionStates.Success(it) }
        }
    }
}