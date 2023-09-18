import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.BirdImage

/**
 * @Author: Umer Dilpazir
 * @Date: 04.09.23.
 */
class BirdsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<BirdsUiState>(BirdsUiState())
    val uiState: StateFlow<BirdsUiState> = _uiState.asStateFlow()


    private val httpClient: HttpClient = HttpClient{
        install(ContentNegotiation){
            json()
        }
    }


    init {
        updateImages()
    }

    override fun onCleared() {
        httpClient.close()
    }

    fun selectedCategory(category: String) {
        _uiState.update {
            it.copy(selectedCategory = category)
        }
    }

    private fun updateImages() {
        viewModelScope.launch {
            val images = getImages()
            _uiState.update {
                it.copy(images = images)
            }
        }
    }

    private suspend fun getImages(): List<BirdImage> {
        val images = httpClient.get("https://sebi.io/demo-image-api/pictures.json")
            .body<List<BirdImage>>()
        return images
    }

}

data class BirdsUiState(
    val images: List<BirdImage> = emptyList(),
    val selectedCategory: String? = "PIGEON"
) {
    val categories : Set<String> = images.map { it.category }.toSet()
    val selectedImage: List<BirdImage> = images.filter { it.category == selectedCategory }
}
