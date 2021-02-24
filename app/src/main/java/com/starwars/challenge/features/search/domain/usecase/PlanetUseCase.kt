package com.starwars.challenge.features.search.domain.usecase

import com.starwars.challenge.features.search.domain.model.PlanetModel
import com.starwars.challenge.features.search.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow

class PlanetUseCase(private val repository: ISearchRepository) : IPlanetUseCase {
    override fun execute(params: String): Flow<PlanetModel> =
        repository.getPlanet(params)
}