package com.starwars.challenge.features.search.data.mappers

import com.starwars.challenge.features.search.data.model.response.CharacterDetailResponse
import com.starwars.challenge.features.search.domain.model.CharacterDetailModel

fun CharacterDetailResponse.mapToModel() = CharacterDetailModel(
    name = name,
    height = height,
    birthYear = birth_year,
    filmUrls = films,
    planetUrl = homeworld,
    speciesUrls = species,
    url = url
)