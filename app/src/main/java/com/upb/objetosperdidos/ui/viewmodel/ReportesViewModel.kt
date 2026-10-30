package com.upb.objetosperdidos.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.upb.objetosperdidos.data.local.AppDatabase
import com.upb.objetosperdidos.data.local.ObjetoEntity
import com.upb.objetosperdidos.data.remote.RetrofitClients
import com.upb.objetosperdidos.data.repository.ObjetoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReportesViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.obtener(application)
    private val repository = ObjetoRepository(
        objetoDao = db.objetoDao(),
        sqliteCloudApi = RetrofitClients.sqliteCloudApi
    )

    val objetos: StateFlow<List<ObjetoEntity>> = repository.observarObjetos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val todosLosObjetos = objetos

    // Método Oficial
    fun insertarObjeto(
        titulo: String,
        descripcion: String,
        latitud: Double,
        longitud: Double,
        direccion: String,
        urlImagen: String
    ) {
        viewModelScope.launch {
            repository.insertarObjeto(0, titulo, descripcion, latitud, longitud, direccion, urlImagen)
        }
    }

    // --- COMPATIBILIDAD CON LA PANTALLA ReportObjectScreen DE ADRI ---
    // Atrapa cualquier parámetro que ella haya configurado en su formulario
    fun agregarObjeto(
        tipoReporte: String = "PERDIDO",
        nombre: String = "",
        categoria: String = "",
        lugar: String = "",
        fecha: String = "",
        imagenRes: Int = 0,
        titulo: String = "",
        descripcion: String = "",
        direccion: String = "",
        urlImagen: String = ""
    ) {
        val finalTitulo = if (nombre.isNotEmpty()) nombre else titulo
        val finalDesc = if (categoria.isNotEmpty()) categoria else descripcion
        val finalDir = if (lugar.isNotEmpty()) lugar else direccion

        insertarObjeto(
            titulo = finalTitulo.ifEmpty { "Sin título" },
            descripcion = finalDesc.ifEmpty { "Sin descripción" },
            latitud = 0.0,
            longitud = 0.0,
            direccion = finalDir.ifEmpty { "Sin ubicación" },
            urlImagen = urlImagen
        )
    }
}
