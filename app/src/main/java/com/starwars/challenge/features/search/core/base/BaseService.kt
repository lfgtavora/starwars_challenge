package com.starwars.challenge.features.search.core.base

import com.starwars.challenge.features.search.data.remote.ApiClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseService {
    companion object {
        private const val BASE_API = "https://swapi.dev/api/"
    }

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val getRetrofitInstance = Retrofit.Builder()
        .baseUrl(BASE_API)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val swapiApi: ApiClient = getRetrofitInstance.create(
        ApiClient::class.java
    )

}