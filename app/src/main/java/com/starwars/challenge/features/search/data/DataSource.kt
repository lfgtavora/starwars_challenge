package com.starwars.challenge.features.search.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataSource {
    fun getData(query: String): Flow<String> = flow {
        emit("return_for_$query") // Emits the result of the request to the flow
    }
}
