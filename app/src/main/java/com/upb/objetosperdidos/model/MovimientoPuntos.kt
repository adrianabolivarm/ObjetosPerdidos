package com.upb.objetosperdidos.model

data class MovimientoPuntos(
    val id: Int,
    val estudianteId: Int,
    val cantidad: Int,
    val tipo: TipoMovimientoPuntos,
    val motivo: String,
    val fecha: String
)

enum class TipoMovimientoPuntos {
    GANANCIA,
    CANJE
}