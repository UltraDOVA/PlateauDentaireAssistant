package com.dova.plateauAssistant.domain

import com.dova.plateauAssistant.data.dao.PlateauDao
import com.dova.plateauAssistant.data.entities.Plateau
import kotlinx.coroutines.flow.Flow

class PlateauRepositoryImpl(private val plateauDao: PlateauDao) : PlateauRepository {
    override fun getAllPlateaux(): Flow<List<Plateau>> = plateauDao.getAllPlateaux()

    override suspend fun insertPlateau(plateau: Plateau) {
        plateauDao.insert(plateau)
    }
}