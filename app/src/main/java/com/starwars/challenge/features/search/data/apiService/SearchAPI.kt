package com.starwars.challenge.features.search.data.apiService

import com.starwars.challenge.features.search.data.model.response.CharacterSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    @GET("people")
    suspend fun searchCharacter(
        @Query("search") query: String
    ): CharacterSearchResponse
}