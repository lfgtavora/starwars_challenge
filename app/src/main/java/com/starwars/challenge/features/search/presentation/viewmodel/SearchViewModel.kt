package com.starwars.challenge.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    companion object {
        private const val DEBOUNCE_DELAY = 1500L
        private const val DEBOUNCE_MIN_CHAR = 3
    }

    private var searchQuery = ""
    private val _suggestionList = MutableStateFlow<SuggestionStates>(SuggestionStates.Empty)
    val suggestionList: StateFlow<SuggestionStates>
        get() = _suggestionList


    sealed class SuggestionStates {
        object Loading : SuggestionStates()
        object Empty : SuggestionStates()
        data class Success(val value: List<Any>) : SuggestionStates()
        data class Error(val message: String) : SuggestionStates()
    }

    fun fetchSearchQuerySuggestions(query: String) {
        _suggestionList.value = SuggestionStates.Loading
        viewModelScope.launch {

            if (query == searchQuery || query.length < DEBOUNCE_MIN_CHAR)
                return@launch

            searchQuery = query

            delay(DEBOUNCE_DELAY)

            _suggestionList.value = SuggestionStates.Success(listOf(searchQuery))
        }
    }

}