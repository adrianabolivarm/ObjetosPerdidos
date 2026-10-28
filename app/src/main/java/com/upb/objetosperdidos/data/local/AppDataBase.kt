package com.upb.objetosperdidos.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ObjetoEntity::class, UbicacionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun objetoDao(): ObjetoDao
    abstract fun ubicacionDao(): UbicacionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun obtener(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "objetos_perdidos.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
