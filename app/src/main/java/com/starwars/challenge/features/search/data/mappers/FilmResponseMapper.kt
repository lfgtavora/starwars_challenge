package com.starwars.challenge.features.search.data.mappers

import com.starwars.challenge.features.search.data.model.response.FilmResponse
import com.starwars.challenge.features.search.domain.model.FilmModel

fun FilmResponse.mapToModel() = FilmModel(
    title = title,
    openingCrawl = opening_crawl
)