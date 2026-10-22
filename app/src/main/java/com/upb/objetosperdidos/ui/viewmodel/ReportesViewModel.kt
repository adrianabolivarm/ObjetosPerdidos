package com.upb.objetosperdidos.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.upb.objetosperdidos.data.local.AppDatabase
import com.upb.objetosperdidos.data.repository.ObjetoRepository
import com.upb.objetosperdidos.model.ObjetoReportado
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReportesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ObjetoRepository

    val objetos: StateFlow<List<ObjetoReportado>>

    init {
        val objetoDao = AppDatabase.getDatabase(application).objetoDao()
        repository = ObjetoRepository(objetoDao)
        objetos = repository.todosLosObjetos.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun agregarObjeto(
        nombre: String,
        descripcion: String,
        categoria: String,
        tipoReporte: String,
        lugar: String,
        fecha: String,
        imagenRes: Int? = null
    ) {
        viewModelScope.launch {
            val nuevoObjeto = ObjetoReportado(
                nombre = nombre,
                descripcion = descripcion,
                categoria = categoria,
                tipoReporte = tipoReporte,
                lugar = lugar,
                fecha = fecha,
                imagenRes = imagenRes
            )
            repository.insertarObjeto(nuevoObjeto)
        }
    }
}
