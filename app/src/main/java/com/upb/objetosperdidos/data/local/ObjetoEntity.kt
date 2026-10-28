package com.upb.objetosperdidos.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "objeto")
data class ObjetoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String = "",
    val descripcion: String = "",
    val latitud: Double = 0.0,
    val longitud: Double = 0.0,
    val direccion: String = "",
    val urlImagen: String = "",
    val estado: String = "PERDIDO"
) {
    // --- PUENTE PARA QUE LA UI DE ADRI NO EXPLOTE ---
    val tipoReporte: String get() = estado
    val nombre: String get() = titulo
    val categoria: String get() = descripcion
    val lugar: String get() = direccion
    val fecha: String get() = "Reciente"
    val imagenRes: Int get() = android.R.drawable.ic_menu_camera
}
