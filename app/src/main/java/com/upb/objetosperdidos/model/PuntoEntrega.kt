package com.upb.objetosperdidos.model

data class PuntoEntrega(
    val id: Int,
    val nombre: String,
    val ubicacion: String,
    val sede: String,
    val activo: Boolean = true
)