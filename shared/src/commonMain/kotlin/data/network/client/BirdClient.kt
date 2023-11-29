package data.network.client

import com.myapplication.shared.data.network.helper.handleErrors
import core.Constants
import data.model.BirdImage
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class BirdClient(
    private val httpClient: HttpClient
) {

    suspend fun getBirdList(): List<BirdImage> {
        return handleErrors {
            httpClient.get(Constants.BASE_URL +"pictures.json") {
                contentType(ContentType.Application.Json)
            }
        }
    }
}