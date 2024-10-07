package com.dova.plateauAssistant.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dova.plateauAssistant.data.dao.PlateauDao
import com.dova.plateauAssistant.data.entities.Plateau
import com.dova.plateauAssistant.utils.Converters

@Database(entities = [Plateau::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PlateauxDatabase : RoomDatabase() {
    abstract fun plateauDao(): PlateauDao

    companion object {
        @Volatile
        private var instance: PlateauxDatabase? = null

        fun getDatabase(context: Context): PlateauxDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, PlateauxDatabase::class.java, "plateaux_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}