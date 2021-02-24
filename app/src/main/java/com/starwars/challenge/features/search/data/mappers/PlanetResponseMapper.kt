package com.starwars.challenge.features.search.data.mappers

import com.starwars.challenge.features.search.data.model.response.PlanetResponse
import com.starwars.challenge.features.search.domain.model.PlanetModel

fun PlanetResponse.mapToModel() = PlanetModel(
    name = name,
    population = population
)