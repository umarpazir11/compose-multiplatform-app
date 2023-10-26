package data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.myapplication.Database
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope
actual fun Scope.sqlDriverFactory(): SqlDriver {
    return AndroidSqliteDriver(Database.Schema, androidContext(), "${DatabaseConstants.name}.db")
}