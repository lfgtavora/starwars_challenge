package com.starwars.challenge.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel: ViewModel() {

//    private val _suggestionList: MutableStateFlow
//    private val suggestionList: StateFlow<SuggestionStates>
//        get() = _suggestionList


    sealed class SuggestionStates {
        object Loading: SuggestionStates()
        data class success(val value: List<Any>): SuggestionStates()
        data class error(val error: Exception): SuggestionStates()
    }

}