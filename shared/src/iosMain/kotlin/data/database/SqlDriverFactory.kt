package data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.myapplication.Database
import core.Constants
import org.koin.core.scope.Scope


actual fun Scope.sqlDriverFactory(): SqlDriver {
    return NativeSqliteDriver(Database.Schema, "${Constants.DATABASE_NAME}.db")
}