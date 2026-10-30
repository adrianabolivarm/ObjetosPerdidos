package com.upb.objetosperdidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.upb.objetosperdidos.data.repository.EstudianteRepository

class AuthViewModelFactory(
    private val estudianteRepository: EstudianteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(estudianteRepository = estudianteRepository) as T
        }
        throw IllegalArgumentException("ViewModel desconocido")
    }
}