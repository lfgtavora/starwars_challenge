package com.starwars.challenge.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starwars.challenge.features.search.domain.model.CharacterDetailModel
import com.starwars.challenge.features.search.domain.model.CharacterModel
import com.starwars.challenge.features.search.domain.usecase.IDetailsUseCase
import com.starwars.challenge.features.search.domain.usecase.ISearchUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: ISearchUseCase,
    private val detailsUseCase: IDetailsUseCase
) : ViewModel() {


    private var searchQuery = ""

    private val _suggestionList2 = MutableStateFlow<SuggestionStates>(SuggestionStates.Empty)
    val suggestionList2: StateFlow<SuggestionStates> = _suggestionList2
    private val _characterDetails = MutableStateFlow<DetailsStates>(DetailsStates.Empty)
    val characterDetails: StateFlow<DetailsStates> = _characterDetails


    fun fetchSearchQuerySuggestions(query: String) {
        if (query == searchQuery || query.length < DEBOUNCE_MIN_CHAR)
            return

        searchQuery = query

        viewModelScope.launch {
            _suggestionList2.value = SuggestionStates.Loading

            delay(DEBOUNCE_DELAY)

            if (query != searchQuery) return@launch

            searchUseCase.execute(query)
                .catch {
                    _suggestionList2.value = SuggestionStates.Error(it.message ?: "Error Searching")
                }
                .collect { _suggestionList2.value = SuggestionStates.Success(it) }
        }
    }

    fun fetchDetails(url: String) {
        viewModelScope.launch {
            detailsUseCase.execute(url)
                .catch {
                    _characterDetails.value = DetailsStates.Error(it.message ?: "Error Details")
                }
                .collect {
                    _characterDetails.value = DetailsStates.Success(it)
                }
        }
    }

    sealed class SuggestionStates {
        object Loading : SuggestionStates()
        object Empty : SuggestionStates()
        data class Success(val value: List<CharacterModel>) : SuggestionStates()
        data class Error(val message: String) : SuggestionStates()
    }

    sealed class DetailsStates {
        object Loading : DetailsStates()
        object Empty : DetailsStates()
        data class Success(val value: CharacterDetailModel) : DetailsStates()
        data class Error(val message: String) : DetailsStates()
    }

    companion object {
        private const val DEBOUNCE_DELAY = 1500L
        private const val DEBOUNCE_MIN_CHAR = 3
    }
}