package com.upb.objetosperdidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upb.objetosperdidos.data.repository.AuthRepository
import com.upb.objetosperdidos.model.Usuario
import com.upb.objetosperdidos.util.Resultado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    val usuarioActual: StateFlow<Usuario?> = repository.currentUserFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(email: String, pass: String) {
        if (email.isBlank() || pass.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Por favor, completa todos los campos.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
            when (val resultado = repository.login(email.trim(), pass)) {
                is Resultado.Success -> {
                    _uiState.update { it.copy(isLoading = false, successMessage = "¡Inicio de sesión exitoso!") }
                }
                is Resultado.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = resultado.message) }
                }
                is Resultado.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun register(email: String, pass: String, nombre: String) {
        if (email.isBlank() || pass.isBlank() || nombre.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Por favor, completa todos los campos.") }
            return
        }
        if (pass.length < 6) {
            _uiState.update { it.copy(errorMessage = "La contraseña debe tener al menos 6 caracteres.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
            when (val resultado = repository.register(email.trim(), pass, nombre.trim())) {
                is Resultado.Success -> {
                    _uiState.update { it.copy(isLoading = false, successMessage = "¡Cuenta creada exitosamente!") }
                }
                is Resultado.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = resultado.message) }
                }
                is Resultado.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun resetPassword(email: String) {
        if (email.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Por favor, ingresa tu correo electrónico para recuperar la contraseña.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
            when (val resultado = repository.resetPassword(email.trim())) {
                is Resultado.Success -> {
                    _uiState.update { it.copy(isLoading = false, successMessage = "Correo de recuperación enviado. Revisa tu bandeja de entrada.") }
                }
                is Resultado.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = resultado.message) }
                }
                is Resultado.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun logout() {
        repository.logout()
    }

    fun limpiarMensajes() {
        _uiState.update { it.copy(errorMessage = null, successMessage = null) }
    }
}
