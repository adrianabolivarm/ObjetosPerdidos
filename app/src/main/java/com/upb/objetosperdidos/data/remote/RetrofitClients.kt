package com.upb.objetosperdidos.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClients {
    val nominatimApi: NominatimApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://nominatim.openstreetmap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NominatimApiService::class.java)
    }

    val imgBBApi: ImgBBApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.imgbb.com/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImgBBApiService::class.java)
    }

    val sqliteCloudApi: SqliteCloudApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://ctntquqjvk.g5.sqlite.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SqliteCloudApiService::class.java)
    }
}
