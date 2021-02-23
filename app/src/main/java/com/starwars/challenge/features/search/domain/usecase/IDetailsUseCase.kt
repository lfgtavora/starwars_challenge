package com.starwars.challenge.features.search.domain.usecase

import com.starwars.challenge.features.search.domain.model.CharacterDetailModel
import kotlinx.coroutines.flow.Flow

interface IDetailsUseCase : BaseUseCase<String, Flow<CharacterDetailModel>>