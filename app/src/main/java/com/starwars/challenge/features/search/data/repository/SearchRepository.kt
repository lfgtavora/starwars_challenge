package com.starwars.challenge.features.search.data.repository

import com.starwars.challenge.features.search.data.apiService.SearchAPI
import com.starwars.challenge.features.search.data.mappers.mapToModel
import com.starwars.challenge.features.search.domain.model.CharacterDetailModel
import com.starwars.challenge.features.search.domain.model.CharacterModel
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
}