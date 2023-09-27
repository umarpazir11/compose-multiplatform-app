package data.repository


import database.BirdsQueries
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import model.BirdImage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class BirdRepository(): KoinComponent {
    //private val database: AppDatabase  = AppDatabase(databaseDriverFactory)
    //val db = database.birdsQueries
    private val apolloClient: BirdsQueries by inject()
    suspend fun syncLocalDatabaseWithServer() {
        getImagesFromServer().forEach {
            println("again ourput: $it")

            apolloClient.insertBird(
                author = it.author+"new",
                category = it.category,
                path = it.path
            )
        }
    }

    private suspend fun getImagesFromServer(): List<BirdImage> {
        return httpClient.get("https://sebi.io/demo-image-api/pictures.json")
            .body()
    }


    private val httpClient: HttpClient = HttpClient{
        install(ContentNegotiation){
            json()
        }
    }


}

