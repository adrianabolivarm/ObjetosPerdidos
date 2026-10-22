package com.upb.objetosperdidos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.upb.objetosperdidos.model.ObjetoReportado
import kotlinx.coroutines.flow.Flow

@Dao
interface ObjetoDao {
    @Query("SELECT * FROM objetos_reportados")
    fun obtenerTodos(): Flow<List<ObjetoReportado>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarObjeto(objeto: ObjetoReportado)
}