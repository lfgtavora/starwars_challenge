package com.starwars.challenge.features.search.domain

import kotlinx.coroutines.flow.Flow

interface ISearchUseCase {
    fun execute(query: String): Flow<String>
}