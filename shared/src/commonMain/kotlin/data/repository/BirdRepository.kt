package data.repository


import com.myapplication.Database
import database.Birds
import data.network.client.BirdClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BirdRepository(db: Database): KoinComponent {
    private val db = db.birdsQueries
    private val birdClient: BirdClient by inject()
    suspend fun syncLocalDatabaseWithServer() {
       return birdClient.getBirdList().forEach {
            db.insertBird(
                author = it.author,
                category = it.category,
                path = it.path
            )
        }
    }

    fun getImagesFromDatabase() : List<Birds> {
        return db.getBirds().executeAsList()
    }

}

