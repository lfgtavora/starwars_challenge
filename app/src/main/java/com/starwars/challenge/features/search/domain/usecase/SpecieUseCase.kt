package com.starwars.challenge.features.search.domain.usecase

import com.starwars.challenge.features.search.domain.model.SpecieModel
import com.starwars.challenge.features.search.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.*

class SpecieUseCase(private val repository: ISearchRepository) : ISpecieUseCase {
    override fun execute(params: String): Flow<SpecieModel> =
        repository.getSpecie(params)
}