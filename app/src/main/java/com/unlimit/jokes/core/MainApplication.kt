package com.unlimit.jokes.core

import android.app.Application
import com.unlimit.jokes.di.appModule
import com.unlimit.jokes.di.databaseModule
import com.unlimit.jokes.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(listOf(
                appModule, networkModule,
                databaseModule
            ))
        }
    }
}