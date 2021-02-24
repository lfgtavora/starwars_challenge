package com.starwars.challenge.features.search.di

import com.starwars.challenge.features.search.data.apiService.SearchAPI
import com.starwars.challenge.features.search.data.repository.SearchRepository
import com.starwars.challenge.features.search.domain.repository.ISearchRepository
import com.starwars.challenge.features.search.presentation.viewmodel.SearchViewModel
import com.example.android.core.newtwork.SwapiService
import com.starwars.challenge.features.search.domain.usecase.*
import com.starwars.challenge.features.search.presentation.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    single { SwapiService.createApiService(SearchAPI::class.java) }

    single<ISearchRepository> { SearchRepository(get()) }

    factory<ISearchUseCase> { SearchUseCase(get()) }
    factory<IDetailsUseCase> { DetailsUseCase(get()) }
    factory<IPlanetUseCase> { PlanetUseCase(get()) }
    factory<ISpecieUseCase> {SpecieUseCase(get())}
    factory<IFilmUseCase> {FilmUseCase(get())}

    viewModel { SearchViewModel(get(), get()) }
    viewModel { DetailsViewModel(get(), get(), get()) }
}