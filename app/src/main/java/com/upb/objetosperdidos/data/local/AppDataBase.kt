package com.upb.objetosperdidos.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.upb.objetosperdidos.model.ObjetoReportado

@Database(entities = [ObjetoReportado::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun objetoDao(): ObjetoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "objetos_perdidos_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}