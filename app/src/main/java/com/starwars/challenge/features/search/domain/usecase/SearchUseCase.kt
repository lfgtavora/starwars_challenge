package com.starwars.challenge.features.search.domain.usecase

import com.starwars.challenge.features.search.data.entity.SuggestionSearchResponse
import com.starwars.challenge.features.search.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

class SearchUseCase(private val repository: SearchRepository) {

    private var searchSuggestionsResponse: SuggestionSearchResponse? = null

    suspend fun fetchSuggestionList(query: String): Flow<SuggestionSearchResponse?> {
        withContext(Dispatchers.IO) {
            searchSuggestionsResponse = repository.callSuggestionSearchList(query)
        }

        return flowOf(searchSuggestionsResponse)
    }

}
