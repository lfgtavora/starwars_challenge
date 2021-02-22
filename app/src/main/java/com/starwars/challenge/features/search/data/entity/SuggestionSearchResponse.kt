package com.starwars.challenge.features.search.data.entity

class SuggestionSearchResponse(
    val results: List<SuggestionResponseResult>?
) {
    class SuggestionResponseResult(
        val name: String,
        val birth_year: String,
        val height: String,
        val films: List<String>,
        val homeworld: String,
        val species: List<String>,
        val url: String
    )
}

