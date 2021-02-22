package com.starwars.challenge.features.search.data.mappers

import com.starwars.challenge.features.search.data.model.CharacterRemoteModel
import com.starwars.challenge.features.search.data.model.response.CharacterSearchResponse
import com.starwars.challenge.features.search.domain.model.Character

fun CharacterSearchResponse.mapToModel(): List<Character> =
    this.results.map { it.mapResponseToModel() }


private fun CharacterRemoteModel.mapResponseToModel() =
    Character(
        name = this.name,
        birthYear = this.birth_year,
        height = this.height,
        url = this.url
    )