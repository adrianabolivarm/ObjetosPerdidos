package com.upb.objetosperdidos.model

data class Canje(
    val id: Int,
    val estudianteId: Int,
    val beneficioId: Int,
    val puntosUtilizados: Int,
    val fecha: String,
    val estado: EstadoCanje = EstadoCanje.REALIZADO
)

enum class EstadoCanje {
    REALIZADO,
    CANCELADO
}