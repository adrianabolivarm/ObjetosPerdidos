package com.upb.objetosperdidos.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "objetos_reportados")
data class ObjetoReportado(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val categoria: String,     // Cambiado a String temporalmente
    val tipoReporte: String,   // Cambiado a String temporalmente
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
