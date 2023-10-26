
import com.myapplication.Database
import data.repository.BirdRepository
import database.Birds
import dev.icerock.moko.mvvm.viewmodel.ViewModel
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
class BirdsViewModel(birdRepository: BirdRepository): ViewModel() {
    private val _uiState = MutableStateFlow<BirdsUiState>(BirdsUiState())
    val uiState: StateFlow<BirdsUiState> = _uiState.asStateFlow()

   // private val db: Database = createDatabase(DriverFactory().createDriver())


    init {
        updateImages(birdRepository)
        //birdRepository.birdsQueries.insertBird("new", "bird", "yes")
        //birdRepository.syncLocalDatabaseWithServer()
       viewModelScope.launch {
           birdRepository.syncLocalDatabaseWithServer()
        }
    }


    fun selectedCategory(category: String) {
        _uiState.update {
            it.copy(selectedCategory = category)
        }
    }

    private fun updateImages(birdRepository: BirdRepository) {
        viewModelScope.launch {
            val images = birdRepository.getImagesFromDatabase()
            _uiState.update {
                it.copy(images = images)
            }
        }
    }
}

data class BirdsUiState(
    val images: List<Birds> = emptyList(),
    val selectedCategory: String? = "PIGEON"
) {
    val categories : Set<String> = images.map { it.category }.toSet()
    val selectedImage: List<Birds> = images.filter { it.category == selectedCategory }
}