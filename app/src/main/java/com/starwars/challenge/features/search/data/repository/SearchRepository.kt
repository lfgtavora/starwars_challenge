package com.starwars.challenge.features.search.data.repository

import com.starwars.challenge.features.search.data.apiService.SearchAPI
import com.starwars.challenge.features.search.data.mappers.mapToModel
import com.starwars.challenge.features.search.domain.model.*
import com.starwars.challenge.features.search.domain.repository.ISearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRepository(val apiService: SearchAPI): ISearchRepository {
    override fun searchCharacters(characterName: String): Flow<List<CharacterModel>> = flow {
        emit(apiService.searchCharacter(characterName).mapToModel())
    }.flowOn(Dispatchers.IO)

    override fun getCharacterDetails(url: String): Flow<CharacterDetailModel> = flow {
        emit(apiService.getCharacterDetail(url).mapToModel())
    }.flowOn(Dispatchers.IO)

    override fun getPlanet(url: String): Flow<PlanetModel> = flow {
        emit(apiService.getPlanet(url).mapToModel())
    }.flowOn(Dispatchers.IO)

    override fun getSpecie(url: String): Flow<SpecieModel> = flow {
        emit(apiService.getSpecie(url).mapToModel())
    }.flowOn(Dispatchers.IO)

    override fun getFilms(url: String): Flow<FilmModel> = flow {
        emit(apiService.getFilm(url).mapToModel())
    }.flowOn(Dispatchers.IO)
}