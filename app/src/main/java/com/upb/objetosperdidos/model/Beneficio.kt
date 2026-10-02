package com.upb.objetosperdidos.model

data class Beneficio(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val costoPuntos: Int,
    val disponible: Boolean = true
)