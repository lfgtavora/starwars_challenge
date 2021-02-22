package com.starwars.challenge.features

import android.app.Application
import com.starwars.challenge.features.search.di.repositoryModule
import com.starwars.challenge.features.search.di.serviceModule
import com.starwars.challenge.features.search.di.useCaseModule
import com.starwars.challenge.features.search.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MainApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    serviceModule,
                    useCaseModule
                )
            )
        }
    }

}