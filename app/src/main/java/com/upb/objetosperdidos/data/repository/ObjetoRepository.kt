package com.upb.objetosperdidos.data.repository

import com.upb.objetosperdidos.data.local.ObjetoDao
import com.upb.objetosperdidos.data.local.ObjetoEntity
import com.upb.objetosperdidos.data.remote.SqliteCloudApiService
import com.upb.objetosperdidos.data.remote.dto.SqliteQueryRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ObjetoRepository(
    private val objetoDao: ObjetoDao,
    private val sqliteCloudApi: SqliteCloudApiService
) {
    private val apiKey = "Bearer 76QYHtRPJD2bSICabrfB22msgUQmr3QcB9RZ7ZsOnB0"

    fun observarObjetos(): Flow<List<ObjetoEntity>> = objetoDao.observarTodos()
    val todosLosObjetos: Flow<List<ObjetoEntity>> get() = observarObjetos()

    suspend fun registrarObjeto(
        id: Int = 0,
        titulo: String,
        descripcion: String,
        latitud: Double,
        longitud: Double,
        direccion: String,
        urlImagen: String
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            val entidad = ObjetoEntity(
                id = id,
                titulo = titulo,
                descripcion = descripcion,
                latitud = latitud,
                longitud = longitud,
                direccion = direccion,
                urlImagen = urlImagen,
                estado = "PERDIDO"
            )

            // 1. Guardar localmente en Room (síncrono)
            objetoDao.insertar(entidad)

            // 2. Sincronizar en la nube en SQLite Cloud
            val tituloSani = titulo.replace("'", "''")
            val descSani = descripcion.replace("'", "''")
            val dirSani = direccion.replace("'", "''")

            val sql = """
                INSERT OR REPLACE INTO objeto (id, titulo, descripcion, latitud, longitud, direccion, url_imagen, estado)
                VALUES ($id, '$tituloSani', '$descSani', $latitud, $longitud, '$dirSani', '$urlImagen', 'PERDIDO');
            """.trimIndent()

            sqliteCloudApi.executeQuery(apiKey, SqliteQueryRequest(sql))
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun insertarObjeto(
        id: Int = 0,
        titulo: String,
        descripcion: String,
        latitud: Double,
        longitud: Double,
        direccion: String,
        urlImagen: String
    ): Boolean = registrarObjeto(id, titulo, descripcion, latitud, longitud, direccion, urlImagen)

    suspend fun insertarObjeto(objeto: ObjetoEntity): Boolean = registrarObjeto(
        id = objeto.id,
        titulo = objeto.titulo,
        descripcion = objeto.descripcion,
        latitud = objeto.latitud,
        longitud = objeto.longitud,
        direccion = objeto.direccion,
        urlImagen = objeto.urlImagen
    )
}
