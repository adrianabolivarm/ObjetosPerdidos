package com.upb.objetosperdidos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UbicacionDao {
    @Query("SELECT * FROM ubicacion")
    fun observarTodas(): Flow<List<UbicacionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertar(ubicacion: UbicacionEntity)
}
