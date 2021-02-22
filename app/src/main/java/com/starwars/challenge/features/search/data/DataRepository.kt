package com.starwars.challenge.features.search.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class DataRepository(
    private val dataSource: DataSource
) {
    /**
     * Returns the favorite latest news applying transformations on the flow.
     * These operations are lazy and don't trigger the flow. They just transform
     * the current value emitted by the flow at that point in time.
     */
    fun fetchData(query: String): Flow<String> =
        dataSource.getData(query)
            // Intermediate operation to filter the list of favorite topics
//            .map { news -> news.filter { userData.isFavoriteTopic(it) } }
            // Intermediate operation to save the latest news in the cache
//            .onEach { news -> saveInCache(news) }
            .flowOn(Dispatchers.IO)
}
