package com.starwars.challenge.features.search.domain.usecase

import com.starwars.challenge.features.search.domain.model.PlanetModel
import kotlinx.coroutines.flow.Flow

interface IPlanetUseCase : BaseUseCase<String, Flow<PlanetModel>>