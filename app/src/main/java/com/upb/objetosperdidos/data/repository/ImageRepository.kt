package com.upb.objetosperdidos.data.repository

import com.upb.objetosperdidos.data.remote.ImgBBApiService
import com.upb.objetosperdidos.domain.mapper.toDomain
import com.upb.objetosperdidos.domain.model.ImagenSubida
import okhttp3.MultipartBody

class ImageRepository(
    private val api: ImgBBApiService
) {
    suspend fun subirImagen(apiKey: String, imagenPart: MultipartBody.Part): ImagenSubida? {
        return try {
            val response = api.uploadImage(apiKey = apiKey, image = imagenPart)
            if (response.isSuccessful) {
                response.body()?.toDomain()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
