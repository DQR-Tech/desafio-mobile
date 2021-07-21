package com.example.desafio

import android.app.Application
import com.example.desafio.di.LocalModule
import com.example.desafio.di.RemotoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@MainApp)
            modules(
                listOf(
                    RemotoModule,
                    LocalModule
                )
            )
        }
    }
}