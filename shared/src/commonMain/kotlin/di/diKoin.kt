package di

import app.cash.sqldelight.db.SqlDriver
import data.createDatabase
import data.repository.BirdRepository
import data.sqlDriverFactory
import database.BirdsQueries
import org.koin.core.context.startKoin
import org.koin.core.module.Module
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
/*    single<Database> {*//**//*
        Database(get<SqlDriver>())
    }*/
   // single<BirdsQueries> { get<BirdsQueries>() }

 //   single { get<SqlDriver>() }

    factory { sqlDriverFactory() }
    single { createDatabase(driver = get()) }

    //single { get<DriverFactory>() }

    factory { BirdRepository(get()) }
}