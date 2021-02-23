package com.starwars.challenge.features.search.data.model.response

data class CharacterDetailResponse(
    val name: String,
    val height: String,
    val birth_year: String,
    val films: List<String>,
    val homeworld: String,
    val species: List<String>,
    val url: String
)