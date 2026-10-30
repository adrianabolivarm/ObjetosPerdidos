package com.upb.objetosperdidos.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estudiantes")
data class EstudianteEntity(
    @PrimaryKey val uid: String,
    val nombre: String,
    val correo: String,
    val carrera: String,
    val sede: String,
    val puntos: Int = 0
)