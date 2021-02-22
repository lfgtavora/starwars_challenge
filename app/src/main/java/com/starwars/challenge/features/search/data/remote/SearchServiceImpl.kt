package com.starwars.challenge.features.search.data.remote

import com.starwars.challenge.features.search.core.base.BaseService
import com.starwars.challenge.features.search.data.entity.SuggestionSearchResponse

class SearchServiceImpl: BaseService(), SearchService {
    override suspend fun getSuggestionSearchList(query: String): SuggestionSearchResponse {
        return swapiApi.searchCharacters(query)
    }
}