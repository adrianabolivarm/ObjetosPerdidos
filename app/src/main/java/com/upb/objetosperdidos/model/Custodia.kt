package com.upb.objetosperdidos.model

data class Custodia(
    val id: Int,
    val objetoId: Int,
    val tipo: TipoCustodia,
    val ubicacion: String? = null,
    val activa: Boolean = true
)

enum class TipoCustodia {
    CON_HALLADOR,
    PUNTO_OFICIAL
}