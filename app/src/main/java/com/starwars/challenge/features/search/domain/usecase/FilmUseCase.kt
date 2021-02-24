package com.starwars.challenge.features.search.domain.usecase

import com.starwars.challenge.features.search.domain.model.FilmModel
import com.starwars.challenge.features.search.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow

class FilmUseCase(private val repository: ISearchRepository) : IFilmUseCase {
    override fun execute(params: String): Flow<FilmModel> =
        repository.getFilms(params)
}