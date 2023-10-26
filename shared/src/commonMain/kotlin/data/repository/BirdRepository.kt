package data.repository


import com.myapplication.Database
import database.Birds
import database.BirdsQueries
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import model.BirdImage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class BirdRepository(db: Database): KoinComponent {
    val db = db.birdsQueries
    suspend fun syncLocalDatabaseWithServer() {
       return getImagesFromServer().forEach {
            println("again ourput: $it")

            db.insertBird(
                author = it.author+"yessss",
                category = it.category,
                path = it.path
            )
        }
    }

    suspend fun getImagesFromServer(): List<BirdImage> {
        return httpClient.get("https://sebi.io/demo-image-api/pictures.json")
            .body()
    }

    suspend fun getImagesFromDatabase() : List<Birds> {
        return db.getBirds("PIGEON").executeAsList()
    }


    private val httpClient: HttpClient = HttpClient{
        install(ContentNegotiation){
            json()
        }
    }


}

