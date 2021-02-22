package com.starwars.challenge.features.search.domain.repository

import com.starwars.challenge.features.search.data.entity.SuggestionSearchResponse
import com.starwars.challenge.features.search.data.remote.SearchService

class SearchRepositoryImpl(private val searchService: SearchService) : SearchRepository {
    override suspend fun callSuggestionSearchList(query: String): SuggestionSearchResponse {
        return searchService.getSuggestionSearchList(query)
    }
}