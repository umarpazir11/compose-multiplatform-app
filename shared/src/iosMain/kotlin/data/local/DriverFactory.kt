package data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.myapplication.Database

actual class DriverFactory {
  actual fun createDriver(): SqlDriver {
    return NativeSqliteDriver(Database.Schema, "birds.db")
  }
}