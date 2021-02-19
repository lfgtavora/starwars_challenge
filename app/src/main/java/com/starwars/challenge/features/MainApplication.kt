package com.starwars.challenge.features

import android.app.Application
import com.starwars.challenge.features.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MainApplication)
            modules(
                listOf(
                    searchModule
                )
            )
        }
    }

}