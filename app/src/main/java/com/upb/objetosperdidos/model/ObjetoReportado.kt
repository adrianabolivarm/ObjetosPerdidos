package com.upb.objetosperdidos.model

data class ObjetoReportado(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val categoria: CategoriaObjeto,
    val tipoReporte: TipoReporte,
    val lugar: String,
    val fecha: String,
    val imagenRes: Int? = null
)

enum class CategoriaObjeto {
    TECNOLOGIA,
    ACCESORIOS,
    DOCUMENTOS,
    ESTUDIO,
    OTROS
}

enum class TipoReporte {
    PERDIDO,
    ENCONTRADO
}