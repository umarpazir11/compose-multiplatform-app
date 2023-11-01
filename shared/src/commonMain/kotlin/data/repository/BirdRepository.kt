package data.repository


import com.myapplication.Database
import data.DatabaseConstants.BASE_URL
import database.Birds
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import model.BirdImage
import org.koin.core.component.KoinComponent

class BirdRepository(db: Database): KoinComponent {
    val db = db.birdsQueries
    suspend fun syncLocalDatabaseWithServer() {
       return getImagesFromServer().forEach {
            db.insertBird(
                author = it.author,
                category = it.category,
                path = it.path
            )
        }
    }

    private suspend fun getImagesFromServer(): List<BirdImage> {
        return httpClient.get(BASE_URL+"pictures.json")
            .body()
    }

    fun getImagesFromDatabase() : List<Birds> {
        return db.getBirds().executeAsList()
    }


    private val httpClient: HttpClient = HttpClient{
        install(ContentNegotiation){
            json()
        }
    }


}

