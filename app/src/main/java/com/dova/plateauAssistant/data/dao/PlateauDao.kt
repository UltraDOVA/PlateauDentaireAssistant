package com.dova.plateauAssistant.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dova.plateauAssistant.data.entities.Plateau
import kotlinx.coroutines.flow.Flow

@Dao
interface PlateauDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(plateau: Plateau)

    @Query("SELECT * from plateaux")//TODO : order by index
    fun getAllPlateaux(): Flow<List<Plateau>>

    @Query("SELECT * from plateaux where id = :id")
    fun getPlateau(id: Int): Flow<Plateau>
}