package di

import com.myapplication.shared.data.network.di.networkModule
import ui.BirdsViewModel
import data.database.createDatabase
import data.repository.BirdRepositoryImpl
import data.database.sqlDriverFactory
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

/**
 * @Author: Umer Dilpazir
 * @Date: 26.09.23.
 */

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(coreModule, networkModule)
    }



// called by iOS client
fun initKoin() = initKoin() {}

val coreModule = module {
    /**
     * Database Injection
     */

    factory { sqlDriverFactory() }

    single { createDatabase(driver = get()) }

    factory { BirdRepositoryImpl(get()) }

    single { BirdsViewModel(get()) }
}