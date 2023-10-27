package data

import app.cash.sqldelight.db.SqlDriver
import com.myapplication.Database
import org.koin.core.scope.Scope

expect fun Scope.sqlDriverFactory(): SqlDriver
fun createDatabase(driver: SqlDriver): Database {
    val database = Database(
        driver = driver,
    )

    return database
}