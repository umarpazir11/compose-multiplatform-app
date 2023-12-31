package ui

import data.model.BirdImage
import data.repository.BirdRepositoryImpl
import database.BirdsEntity
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @Author: Umer Dilpazir
 * @Date: 04.09.23.
 */
class BirdsViewModel(birdRepository: BirdRepositoryImpl) : ViewModel() {
    private val _uiState = MutableStateFlow<BirdsUiState>(BirdsUiState())
    val uiState: StateFlow<BirdsUiState> = _uiState.asStateFlow()

    init {
        println("init started")
        viewModelScope.launch {
            birdRepository.syncLocalDatabaseWithServer()
        }.isCompleted
    }

    fun selectedCategory(birdRepo: BirdRepositoryImpl, category: String) {
        _uiState.update {
            it.copy(selectedCategory = category)
        }
        updateImages(birdRepo)
    }

    private fun updateImages(birdRepository: BirdRepositoryImpl) {
        viewModelScope.launch {
            val images = birdRepository.getImagesFromDatabase()
            _uiState.update {
                it.copy(images = images)
            }
        }.isCompleted
    }
}

data class BirdsUiState(
    val images: List<BirdImage> = emptyList(),
    val selectedCategory: String? = null
) {
    val allImages: List<BirdImage> = images
}