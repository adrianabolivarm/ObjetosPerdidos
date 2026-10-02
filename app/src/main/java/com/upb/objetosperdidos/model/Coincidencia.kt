package com.upb.objetosperdidos.model

data class Coincidencia(
    val id: Int,
    val objetoPerdidoId: Int,
    val objetoEncontradoId: Int,
    val categoria: CategoriaObjeto,
    val estado: EstadoCoincidencia = EstadoCoincidencia.NUEVA
)

enum class EstadoCoincidencia {
    NUEVA,
    POR_REVISAR,
    REVISADA
}