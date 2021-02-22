package com.starwars.challenge.features.search.data.remote

import com.starwars.challenge.features.search.data.entity.SuggestionSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiClient {

    @GET("people/")
    suspend fun searchCharacters(@Query("search") params: String): SuggestionSearchResponse

//    @GET
//    suspend fun fetchCharacterDetail(@Url url: String): CharacterRemoteModel
//
//    @GET
//    suspend fun fetchSpecieDetails(@Url speciesUrl: String): SpecieResponse
//
//    @GET
//    suspend fun fetchFilmDetails(@Url filmsUrl: String): FilmResponse
//
//    @GET
//    suspend fun fetchPlanet(@Url characterUrl: String): PlanetResponse

}