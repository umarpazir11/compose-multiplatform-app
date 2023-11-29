package com.myapplication.shared.data.network.di

import data.network.client.BirdClient
import data.network.createHttpClient
import org.koin.dsl.module

val networkModule =
    module {
        single { createHttpClient() }
        single { BirdClient(httpClient = get()) }
    }