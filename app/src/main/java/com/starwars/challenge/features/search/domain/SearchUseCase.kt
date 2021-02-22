package com.starwars.challenge.features.search.domain

import com.starwars.challenge.features.search.data.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce

class SearchUseCase(private val repository: DataRepository): ISearchUseCase {
    override fun execute(query: String): Flow<String> = repository.fetchData(query).debounce(1500L)
}