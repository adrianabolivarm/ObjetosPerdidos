package com.upb.objetosperdidos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EstudianteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEstudiante(estudiante: EstudianteEntity)

    @Query("SELECT * FROM estudiantes WHERE uid = :uid")
    suspend fun getEstudiante(uid: String): EstudianteEntity?
}