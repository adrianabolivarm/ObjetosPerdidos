package com.upb.objetosperdidos.data.repository

import com.upb.objetosperdidos.data.local.ObjetoDao
import com.upb.objetosperdidos.model.ObjetoReportado
import kotlinx.coroutines.flow.Flow

class ObjetoRepository(private val dao: ObjetoDao) {
    val todosLosObjetos: Flow<List<ObjetoReportado>> = dao.obtenerTodos()

    suspend fun insertarObjeto(objeto: ObjetoReportado) {
        dao.insertarObjeto(objeto)
    }
}
