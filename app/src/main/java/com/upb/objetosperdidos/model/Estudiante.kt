package com.upb.objetosperdidos.model

data class Estudiante(
    val id: Int,
    val nombreCompleto: String,
    val codigoEstudiante: String,
    val correo: String,
    val carrera: String,
    val sede: String,
    val puntos: Int = 0
)