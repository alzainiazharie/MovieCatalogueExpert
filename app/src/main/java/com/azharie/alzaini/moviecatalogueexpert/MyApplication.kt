package com.azharie.alzaini.moviecatalogueexpert

import android.app.Application
import com.azharie.alzaini.moviecatalogueexpert.core.di.dbModule
import com.azharie.alzaini.moviecatalogueexpert.core.di.networkModule
import com.azharie.alzaini.moviecatalogueexpert.core.di.repositoryModule
import com.azharie.alzaini.moviecatalogueexpert.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    dbModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}