package com.starwars.challenge.features.search.domain.usecase

import com.starwars.challenge.features.search.domain.model.SpecieModel
import kotlinx.coroutines.flow.Flow

interface ISpecieUseCase : BaseUseCase<String, Flow<SpecieModel>>