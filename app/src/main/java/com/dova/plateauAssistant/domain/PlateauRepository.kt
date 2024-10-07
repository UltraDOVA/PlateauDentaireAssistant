package com.dova.plateauAssistant.domain

import com.dova.plateauAssistant.data.entities.Plateau
import kotlinx.coroutines.flow.Flow

interface PlateauRepository {
    fun getAllPlateaux(): Flow<List<Plateau>>
    suspend fun getPlateau(id: Int): Plateau
    suspend fun insertPlateau(plateau: Plateau)
}