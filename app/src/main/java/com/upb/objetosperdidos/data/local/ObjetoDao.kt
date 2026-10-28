package com.upb.objetosperdidos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ObjetoDao {
    @Query("SELECT * FROM objeto")
    fun observarTodos(): Flow<List<ObjetoEntity>>

    @Query("SELECT * FROM objeto WHERE id = :id LIMIT 1")
    fun obtenerPorId(id: Int): ObjetoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertar(objeto: ObjetoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarTodos(objetos: List<ObjetoEntity>)
}
