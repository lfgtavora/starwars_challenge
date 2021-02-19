package com.starwars.challenge.features.search.di

import com.starwars.challenge.features.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    viewModel { SearchViewModel() }

}