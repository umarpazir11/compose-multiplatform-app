package domain.repository

import data.model.BirdImage

interface BirdRepository {
    suspend fun syncLocalDatabaseWithServer()
    fun getImagesFromDatabase(): List<BirdImage>
}