package com.starwars.challenge.features.search.data.remote

import com.starwars.challenge.features.search.data.entity.SuggestionSearchResponse

interface SearchService {
    suspend fun getSuggestionSearchList(query: String): SuggestionSearchResponse
}