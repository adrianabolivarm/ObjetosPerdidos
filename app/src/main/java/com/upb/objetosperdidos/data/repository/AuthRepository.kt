package com.upb.objetosperdidos.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.userProfileChangeRequest
import com.upb.objetosperdidos.model.Usuario
import com.upb.objetosperdidos.util.Resultado
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {

    val currentUserFlow: Flow<Usuario?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val firebaseUser = firebaseAuth.currentUser
            val usuario = firebaseUser?.let {
                Usuario(
                    uid = it.uid,
                    email = it.email ?: "",
                    nombre = it.displayName
                )
            }
            trySend(usuario)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    suspend fun login(email: String, pass: String): Resultado<Usuario> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, pass).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                Resultado.Success(
                    Usuario(
                        uid = firebaseUser.uid,
                        email = firebaseUser.email ?: email,
                        nombre = firebaseUser.displayName
                    )
                )
            } else {
                Resultado.Error("No se pudo obtener el usuario autenticado.")
            }
        } catch (e: Exception) {
            Resultado.Error(traducirError(e))
        }
    }

    suspend fun register(email: String, pass: String, nombre: String? = null): Resultado<Usuario> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, pass).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                if (!nombre.isNullOrBlank()) {
                    val profileUpdates = userProfileChangeRequest {
                        displayName = nombre
                    }
                    firebaseUser.updateProfile(profileUpdates).await()
                }
                Resultado.Success(
                    Usuario(
                        uid = firebaseUser.uid,
                        email = firebaseUser.email ?: email,
                        nombre = nombre ?: firebaseUser.displayName
                    )
                )
            } else {
                Resultado.Error("No se pudo registrar el usuario.")
            }
        } catch (e: Exception) {
            Resultado.Error(traducirError(e))
        }
    }

    suspend fun resetPassword(email: String): Resultado<Unit> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Resultado.Success(Unit)
        } catch (e: Exception) {
            Resultado.Error(traducirError(e))
        }
    }

    fun logout() {
        auth.signOut()
    }

    private fun traducirError(e: Exception): String {
        return when (e) {
            is FirebaseAuthInvalidUserException -> "El correo electrónico no está registrado o la cuenta ha sido deshabilitada."
            is FirebaseAuthInvalidCredentialsException -> "Las credenciales son incorrectas o el formato del correo no es válido."
            is FirebaseAuthUserCollisionException -> "Ya existe una cuenta registrada con este correo electrónico."
            is FirebaseAuthWeakPasswordException -> "La contraseña es demasiado débil. Debe tener al menos 6 caracteres."
            else -> e.localizedMessage ?: "Ocurrió un error inesperado. Inténtalo de nuevo."
        }
    }
}
