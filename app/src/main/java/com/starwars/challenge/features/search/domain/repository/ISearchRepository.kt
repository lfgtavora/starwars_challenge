package com.starwars.challenge.features.search.domain.repository

import com.starwars.challenge.features.search.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    fun searchCharacters(characterName: String): Flow<List<Character>>
}
