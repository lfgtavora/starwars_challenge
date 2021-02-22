package com.starwars.challenge.features.search.di

import com.starwars.challenge.features.search.data.DataRepository
import com.starwars.challenge.features.search.data.DataSource
import com.starwars.challenge.features.search.domain.usecase.ISearchUseCase
import com.starwars.challenge.features.search.domain.usecase.SearchUseCase
import com.starwars.challenge.features.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    single { DataSource() }
    single { DataRepository(get()) }
    factory<ISearchUseCase> {
        SearchUseCase(
            get()
        )
    }
    viewModel { SearchViewModel(get()) }

}