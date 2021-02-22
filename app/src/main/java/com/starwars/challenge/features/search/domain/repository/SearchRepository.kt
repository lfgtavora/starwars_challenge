package com.starwars.challenge.features.search.domain.repository

import com.starwars.challenge.features.search.data.entity.SuggestionSearchResponse

interface SearchRepository {
    suspend fun callSuggestionSearchList(query: String): SuggestionSearchResponse
}
