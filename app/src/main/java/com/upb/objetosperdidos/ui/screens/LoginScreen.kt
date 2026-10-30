package com.upb.objetosperdidos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.upb.objetosperdidos.data.local.AppDatabase
import com.upb.objetosperdidos.data.remote.RetrofitClient
import com.upb.objetosperdidos.data.repository.EstudianteRepository
import com.upb.objetosperdidos.ui.viewmodel.AuthViewModel
import com.upb.objetosperdidos.ui.viewmodel.AuthViewModelFactory

private val PrimaryBlue = Color(0xFF1689FF)
private val BackgroundColor = Color(0xFFF7F7FB)
private val TextPrimary = Color(0xFF181820)
private val TextSecondary = Color(0xFF777783)
private val ErrorColor = Color(0xFFD32F2F)
private val SuccessColor = Color(0xFF2DBE7F)

enum class AuthMode {
    LOGIN,
    REGISTER,
    FORGOT_PASSWORD
}

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {}
) {
    // 1. Inicialización de dependencias para la base de datos local y remota
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val estudianteRepository = remember {
        EstudianteRepository(
            estudianteDao = db.estudianteDao(),
            sqliteCloudService = RetrofitClient.sqliteCloudApiService
        )
    }

    // 2. Instanciación del AuthViewModel usando la fábrica con EstudianteRepository
    val viewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(estudianteRepository)
    )

    val uiState by viewModel.uiState.collectAsState()
    val currentUser by viewModel.usuarioActual.collectAsState()

    if (currentUser != null) {
        onLoginSuccess()
    }

    var authMode by remember { mutableStateOf(AuthMode.LOGIN) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = BackgroundColor
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Objetos Perdidos UPB",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = when (authMode) {
                            AuthMode.LOGIN -> "Inicia sesión en tu cuenta"
                            AuthMode.REGISTER -> "Crea una nueva cuenta"
                            AuthMode.FORGOT_PASSWORD -> "Recupera tu contraseña"
                        },
                        fontSize = 14.sp,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    if (authMode == AuthMode.REGISTER) {
                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it; viewModel.limpiarMensajes() },
                            label = { Text("Nombre completo") },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = PrimaryBlue) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it; viewModel.limpiarMensajes() },
                        label = { Text("Correo electrónico institucional") },
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = PrimaryBlue) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (authMode != AuthMode.FORGOT_PASSWORD) {
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it; viewModel.limpiarMensajes() },
                            label = { Text("Contraseña") },
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = PrimaryBlue) },
                            trailingIcon = {
                                val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(imageVector = image, contentDescription = "Toggle password visibility", tint = TextSecondary)
                                }
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    if (uiState.errorMessage != null) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = uiState.errorMessage!!,
                            color = ErrorColor,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    if (uiState.successMessage != null) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = uiState.successMessage!!,
                            color = SuccessColor,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            when (authMode) {
                                AuthMode.LOGIN -> viewModel.login(email, password)
                                AuthMode.REGISTER -> viewModel.register(email, password, nombre)
                                AuthMode.FORGOT_PASSWORD -> viewModel.resetPassword(email)
                            }
                        },
                        enabled = !uiState.isLoading,
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(
                                text = when (authMode) {
                                    AuthMode.LOGIN -> "Iniciar Sesión"
                                    AuthMode.REGISTER -> "Registrarse"
                                    AuthMode.FORGOT_PASSWORD -> "Enviar Enlace de Recuperación"
                                },
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    when (authMode) {
                        AuthMode.LOGIN -> {
                            TextButton(onClick = { authMode = AuthMode.FORGOT_PASSWORD; viewModel.limpiarMensajes() }) {
                                Text("¿Olvidaste tu contraseña?", color = PrimaryBlue, fontSize = 13.sp)
                            }
                            TextButton(onClick = { authMode = AuthMode.REGISTER; viewModel.limpiarMensajes() }) {
                                Text("¿No tienes cuenta? Regístrate aquí", color = TextSecondary, fontSize = 13.sp)
                            }
                        }
                        AuthMode.REGISTER -> {
                            TextButton(onClick = { authMode = AuthMode.LOGIN; viewModel.limpiarMensajes() }) {
                                Text("¿Ya tienes cuenta? Inicia sesión", color = PrimaryBlue, fontSize = 13.sp)
                            }
                        }
                        AuthMode.FORGOT_PASSWORD -> {
                            TextButton(onClick = { authMode = AuthMode.LOGIN; viewModel.limpiarMensajes() }) {
                                Text("Volver al inicio de sesión", color = PrimaryBlue, fontSize = 13.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}