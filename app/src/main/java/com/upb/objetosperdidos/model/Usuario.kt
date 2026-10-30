package com.upb.objetosperdidos.model

data class Usuario(
    val uid: String,
    val email: String,
    val nombre: String? = null
)
