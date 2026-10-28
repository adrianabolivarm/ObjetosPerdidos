package com.upb.objetosperdidos.data.repository

import com.upb.objetosperdidos.data.local.UbicacionDao
import com.upb.objetosperdidos.data.local.UbicacionEntity
import com.upb.objetosperdidos.data.remote.NominatimApiService
import com.upb.objetosperdidos.domain.mapper.toDomain
import com.upb.objetosperdidos.domain.model.Ubicacion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationRepository(
    private val api: NominatimApiService,
    private val ubicacionDao: UbicacionDao? = null
) {
    suspend fun buscarUbicacion(consulta: String): List<Ubicacion> = withContext(Dispatchers.IO) {
        try {
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

    suspend fun guardarUbicacion(nombreDireccion: String, latitud: Double, longitud: Double) = withContext(Dispatchers.IO) {
        ubicacionDao?.insertar(
            UbicacionEntity(
                nombreDireccion = nombreDireccion,
                latitud = latitud,
                longitud = longitud
            )
        )
    }
}
