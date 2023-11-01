package data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.myapplication.Database
import org.koin.core.scope.Scope


actual fun Scope.sqlDriverFactory(): SqlDriver {
    return NativeSqliteDriver(Database.Schema, "${DatabaseConstants.DATABASE_NAME}.db")
}