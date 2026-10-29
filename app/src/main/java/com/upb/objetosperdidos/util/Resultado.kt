package com.upb.objetosperdidos.util

sealed class Resultado<out T> {
    data class Success<out T>(val data: T) : Resultado<T>()
    data class Error(val message: String) : Resultado<Nothing>()
    data object Loading : Resultado<Nothing>()
}
