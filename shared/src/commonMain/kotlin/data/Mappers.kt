package data

import data.model.BirdImage
import database.BirdsEntity

/**
 * @Author: Umer Dilpazir
 * @Date: 02.11.23.
 */
fun BirdsEntity.toBirdImage() = BirdImage(
    author = author,
    category = category,
    path =path
)
