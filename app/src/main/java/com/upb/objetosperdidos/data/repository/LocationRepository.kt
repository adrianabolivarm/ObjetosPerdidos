package com.upb.objetosperdidos.data.repository

import com.upb.objetosperdidos.data.remote.NominatimApiService
import com.upb.objetosperdidos.domain.mapper.toDomain
import com.upb.objetosperdidos.domain.model.Ubicacion

class LocationRepository(
    private val api: NominatimApiService
) {
    suspend fun buscarUbicacion(consulta: String): List<Ubicacion> {
        return try {
            val response = api.searchLocation(query = consulta)
            if (response.isSuccessful) {
                response.body()?.map { it.toDomain() }.orEmpty()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
