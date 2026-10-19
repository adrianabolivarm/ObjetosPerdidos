package com.upb.objetosperdidos.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImgBBResponseDto(
    val data: ImgBBDataDto?,
    val success: Boolean,
    val status: Int
)

data class ImgBBDataDto(
    val id: String,
    val url: String,
    @SerializedName("display_url") val displayUrl: String,
    @SerializedName("delete_url") val deleteUrl: String
)
