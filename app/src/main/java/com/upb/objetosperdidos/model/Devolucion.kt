package com.upb.objetosperdidos.model

data class Devolucion(
    val id: Int,
    val objetoId: Int,
    val estudiantePropietarioId: Int,
    val estudianteHalladorId: Int?,
    val fecha: String,
    val puntosOtorgados: Int = 0,
    val estado: EstadoDevolucion = EstadoDevolucion.COMPLETADA
)

enum class EstadoDevolucion {
    PENDIENTE,
    COMPLETADA
}