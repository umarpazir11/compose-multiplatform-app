package data.local

import com.myapplication.Database

class AppDatabase(databaseDriverFactory: DriverFactory) {
    private val database = Database(databaseDriverFactory.createDriver())
    private val dbQuery = database.birdsQueries

    fun insertBird(author: String, category: String, path: String) {
        dbQuery.transaction {
            dbQuery.insertBird(author, category, path)
        }
    }
}