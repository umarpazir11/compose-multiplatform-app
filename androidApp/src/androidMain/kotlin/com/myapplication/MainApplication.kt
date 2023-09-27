package com.myapplication

import android.app.Application
import android.content.Context
import di.sharedModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
/*        startKoin {
            modules(
                module {
                    single<Context> { this@MainApplication }
                },
                *sharedModules
            )
        }*/

    }
}