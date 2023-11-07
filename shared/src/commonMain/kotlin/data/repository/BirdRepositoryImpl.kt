package data.repository


import com.myapplication.Database
import data.model.BirdImage
import data.network.client.BirdClient
import data.toBirdImage
import domain.repository.BirdRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BirdRepositoryImpl(db: Database): BirdRepository, KoinComponent {
    private val db = db.birdsQueries
    private val birdClient: BirdClient by inject()
    override suspend fun syncLocalDatabaseWithServer() {
       return birdClient.getBirdList().forEach {
            db.insertBird(
                author = it.author,
                category = it.category,
                path = it.path
            )
        }
    }

    override fun getImagesFromDatabase() : List<BirdImage> {
        return db.getBirds().executeAsList().map { it.toBirdImage() }
    }

}

