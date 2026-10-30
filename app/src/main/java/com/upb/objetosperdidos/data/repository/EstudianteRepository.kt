package com.upb.objetosperdidos.data.repository

import com.upb.objetosperdidos.data.local.EstudianteDao
import com.upb.objetosperdidos.data.local.EstudianteEntity
import com.upb.objetosperdidos.data.remote.SqliteCloudApiService
import com.upb.objetosperdidos.data.remote.dto.SqliteQueryRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EstudianteRepository(
    private val estudianteDao: EstudianteDao,
    private val sqliteCloudApi: SqliteCloudApiService
) {
    suspend fun registrarEstudiante(estudiante: EstudianteEntity) = withContext(Dispatchers.IO) {
        // 1. Guardar los datos localmente en Room
        estudianteDao.insertEstudiante(estudiante)

        // 2. Preparar el comando SQL para la nube (SQLite Cloud)
        val querySql = """
            INSERT INTO estudiantes (uid, nombre, correo, carrera, sede, puntos)
            VALUES ('${estudiante.uid}', '${estudiante.nombre}', '${estudiante.correo}', '${estudiante.carrera}', '${estudiante.sede}', ${estudiante.puntos})
        """.trimIndent()

        // 3. Enviar a SQLite Cloud
        try {
            val request = SqliteQueryRequest(sql = querySql)
            val token = "Bearer 76QYHtRPJD2bSICabrfB22msgUQmr3QcB9RZ7ZsOnB0"

            sqliteCloudApi.executeQuery(token, request)
        } catch (e: Exception) {
            // Si falla el internet, Room ya aseguró los datos locales
            e.printStackTrace()
        }
    }
}