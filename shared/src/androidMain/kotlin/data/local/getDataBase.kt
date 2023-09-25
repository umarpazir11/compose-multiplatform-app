package data.local

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.myapplication.Database

actual fun getDataBase(): Database {
    return Database(AndroidSqliteDriver(Database.Schema, MyApplication.appContext, "birds.db"))
}