package com.starwars.challenge.features.search.domain.usecase

import com.starwars.challenge.features.search.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface ISearchUseCase : BaseUseCase<String, Flow<List<Character>>>