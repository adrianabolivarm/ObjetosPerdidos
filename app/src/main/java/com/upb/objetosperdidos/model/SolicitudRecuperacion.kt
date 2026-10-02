package com.upb.objetosperdidos.model

data class SolicitudRecuperacion(
    val id: Int,
    val estudianteId: Int,
    val publicacionId: Int,
    val descripcionPrueba: String,
    val estado: EstadoSolicitud = EstadoSolicitud.PENDIENTE
)

enum class EstadoSolicitud {
    PENDIENTE,
    APROBADA,
    RECHAZADA
}