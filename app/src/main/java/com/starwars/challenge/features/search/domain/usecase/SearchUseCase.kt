package com.starwars.challenge.features.search.domain.usecase

import com.starwars.challenge.features.search.domain.model.CharacterModel
import com.starwars.challenge.features.search.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCase(private val repository: ISearchRepository) : ISearchUseCase {
    override fun execute(params: String): Flow<List<CharacterModel>> =
        repository.searchCharacters(params)
}