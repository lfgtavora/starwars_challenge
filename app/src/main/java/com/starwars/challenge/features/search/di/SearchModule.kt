package com.starwars.challenge.features.search.di

import com.starwars.challenge.features.search.data.apiService.SearchAPI
import com.starwars.challenge.features.search.data.repository.SearchRepository
import com.starwars.challenge.features.search.domain.repository.ISearchRepository
import com.starwars.challenge.features.search.domain.usecase.ISearchUseCase
import com.starwars.challenge.features.search.domain.usecase.SearchUseCase
import com.starwars.challenge.features.search.presentation.viewmodel.SearchViewModel
import com.example.android.core.newtwork.SwapiService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    single { SwapiService.createApiService(SearchAPI::class.java) }

    single<ISearchRepository> { SearchRepository(get()) }

    factory<ISearchUseCase> {
        SearchUseCase(
            get()
        )
    }

    viewModel { SearchViewModel(get()) }
}