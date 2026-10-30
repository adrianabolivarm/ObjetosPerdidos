package com.upb.objetosperdidos.data.remote

import com.upb.objetosperdidos.data.remote.dto.SqliteCloudResponse
import com.upb.objetosperdidos.data.remote.dto.SqliteQueryRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SqliteCloudApiService {
    @POST("v1/json")
    suspend fun executeQuery(
        @Header("Authorization") bearerToken: String,
        @Body request: SqliteQueryRequest
    ): Response<SqliteCloudResponse>
}
