package com.upb.objetosperdidos.data.remote

import com.upb.objetosperdidos.data.remote.dto.LocationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NominatimApiService {
    @GET("search")
    suspend fun searchLocation(
        @Query("q") query: String,
        @Query("format") format: String = "json",
        @Header("User-Agent") userAgent: String = "ObjetosPerdidosApp/1.0"
    ): Response<List<LocationDto>>
}
