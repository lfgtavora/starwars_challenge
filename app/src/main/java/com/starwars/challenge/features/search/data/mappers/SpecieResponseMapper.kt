package com.starwars.challenge.features.search.data.mappers

import com.starwars.challenge.features.search.data.model.response.SpecieResponse
import com.starwars.challenge.features.search.domain.model.SpecieModel

fun SpecieResponse.mapToModel() = SpecieModel(
    name = name,
    language = language,
    homeWorld = homeworld.orEmpty()
)