package com.upb.objetosperdidos.model

data class Publicacion(
    val id: Int,
    val objeto: ObjetoReportado,
    val estudianteId: Int,
    val fechaPublicacion: String,
    val estado: EstadoPublicacion = EstadoPublicacion.ACTIVA
)

enum class EstadoPublicacion {
    ACTIVA,
    CERRADA,
    RECUPERADA
}