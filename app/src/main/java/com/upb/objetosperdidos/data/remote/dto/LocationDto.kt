package com.upb.objetosperdidos.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("place_id") val placeId: Long,
    @SerializedName("display_name") val displayName: String,
    @SerializedName("lat") val lat: String,
    @SerializedName("lon") val lon: String
)
