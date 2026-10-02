package com.upb.objetosperdidos.model

data class Encargado(
    val id: Int,
    val nombreCompleto: String,
    val correo: String,
    val puntoEntregaId: Int,
    val activo: Boolean = true
)