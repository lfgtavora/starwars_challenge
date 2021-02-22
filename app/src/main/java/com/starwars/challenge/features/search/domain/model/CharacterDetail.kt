package com.starwars.challenge.features.search.domain.model

data class CharacterDetail(
    val filmUrls: List<String>,
    val planetUrl: String,
    val speciesUrls: List<String>,
    val url: String
)
