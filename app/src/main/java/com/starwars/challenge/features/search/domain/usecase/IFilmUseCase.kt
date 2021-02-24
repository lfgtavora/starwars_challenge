package com.starwars.challenge.features.search.domain.usecase

import com.starwars.challenge.features.search.domain.model.FilmModel
import kotlinx.coroutines.flow.Flow

interface IFilmUseCase : BaseUseCase<String, Flow<FilmModel>>