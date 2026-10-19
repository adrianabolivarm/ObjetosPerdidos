package com.upb.objetosperdidos.domain.model

data class Ubicacion(
    val id: Long,
    val nombreDireccion: String,
    val latitud: Double,
    val longitud: Double
)
