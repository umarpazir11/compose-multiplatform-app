package di

import data.createDatabase
import data.repository.BirdRepository
import data.sqlDriverFactory
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
        modules(coreModule)
    }



// called by iOS client
fun initKoin() = initKoin() {}

val coreModule = module {
    /**
     * Database Injection
     */

    factory { sqlDriverFactory() }

    single { createDatabase(driver = get()) }

    factory { BirdRepository(get()) }
}