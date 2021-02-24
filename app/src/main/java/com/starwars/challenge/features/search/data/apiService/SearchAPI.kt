package com.starwars.challenge.features.search.data.apiService

import com.starwars.challenge.features.search.data.model.response.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface SearchAPI {
    @GET("people/")
    suspend fun searchCharacter(@Query("search") query: String): CharacterSearchResponse

    @GET
    suspend fun getCharacterDetail(@Url url: String): CharacterDetailResponse

    @GET
    suspend fun getPlanet(@Url url: String): PlanetResponse

    @GET
    suspend fun getSpecie(@Url url: String): SpecieResponse

    @GET
    suspend fun getFilm(@Url url: String): FilmResponse
}