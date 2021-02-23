package com.starwars.challenge.features.search.domain.repository

import com.starwars.challenge.features.search.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    fun searchCharacters(characterName: String): Flow<List<CharacterModel>>
}
