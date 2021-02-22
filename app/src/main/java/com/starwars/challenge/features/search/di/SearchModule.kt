package com.starwars.challenge.features.search.di

import com.starwars.challenge.features.search.data.remote.SearchService
import com.starwars.challenge.features.search.data.remote.SearchServiceImpl
import com.starwars.challenge.features.search.domain.repository.SearchRepository
import com.starwars.challenge.features.search.domain.repository.SearchRepositoryImpl
import com.starwars.challenge.features.search.domain.usecase.SearchUseCase
import com.starwars.challenge.features.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
}

val repositoryModule = module {
    single<SearchRepository> {
        SearchRepositoryImpl(
            get()
        )
    }
}

val serviceModule = module {
    single<SearchService> { SearchServiceImpl() }
}


val useCaseModule = module {
    single {
        SearchUseCase(
            get()
        )
    }
}