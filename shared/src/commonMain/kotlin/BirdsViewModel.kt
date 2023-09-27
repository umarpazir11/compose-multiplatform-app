
import data.repository.BirdRepository
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
        //updateImages()
       viewModelScope.launch {
           birdRepository.syncLocalDatabaseWithServer()
        }
    }


    fun selectedCategory(category: String) {
        _uiState.update {
            it.copy(selectedCategory = category)
        }
    }

    private fun updateImages() {
/*        viewModelScope.launch {
            val images = getImages()
            _uiState.update {
                it.copy(images = images)
            }
        }*/
    }
}

data class BirdsUiState(
    val images: List<BirdImage> = emptyList(),
    val selectedCategory: String? = "PIGEON"
) {
    val categories : Set<String> = images.map { it.category }.toSet()
    val selectedImage: List<BirdImage> = images.filter { it.category == selectedCategory }
}