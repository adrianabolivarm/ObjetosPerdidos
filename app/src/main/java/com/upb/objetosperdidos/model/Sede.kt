package com.upb.objetosperdidos.model

data class Sede(
    val id: Int,
    val nombre: String,
    val ciudad: String,
    val direccion: String? = null,
    val activa: Boolean = true
)