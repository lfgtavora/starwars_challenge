package com.starwars.challenge.features.search.domain.usecase

import com.starwars.challenge.features.search.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface ISearchUseCase : BaseUseCase<String, Flow<List<CharacterModel>>>