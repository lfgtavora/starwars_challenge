package com.starwars.challenge.features.search.domain.repository

import com.starwars.challenge.features.search.domain.model.*
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    fun searchCharacters(characterName: String): Flow<List<CharacterModel>>
    fun getCharacterDetails(url: String): Flow<CharacterDetailModel>
    fun getPlanet(url: String): Flow<PlanetModel>
    fun getSpecie(url: String): Flow<SpecieModel>
    fun getFilms(url: String): Flow<FilmModel>
}

