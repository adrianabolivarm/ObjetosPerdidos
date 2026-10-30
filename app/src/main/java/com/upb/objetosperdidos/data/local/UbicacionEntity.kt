package com.upb.objetosperdidos.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ubicacion")
data class UbicacionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombreDireccion: String = "",
    val latitud: Double = 0.0,
    val longitud: Double = 0.0
)
