package com.starwars.challenge.features.search.domain.usecase

import com.starwars.challenge.features.search.domain.model.CharacterDetailModel
import com.starwars.challenge.features.search.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow

class DetailsUseCase(private val repository: ISearchRepository): IDetailsUseCase {
    override fun execute(params: String): Flow<CharacterDetailModel> =
        repository.getCharacterDetails(params)
}