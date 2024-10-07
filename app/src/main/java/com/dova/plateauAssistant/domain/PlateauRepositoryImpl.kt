package com.dova.plateauAssistant.domain

import com.dova.plateauAssistant.data.dao.PlateauDao
import com.dova.plateauAssistant.data.entities.Plateau
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take

class PlateauRepositoryImpl(private val plateauDao: PlateauDao) : PlateauRepository {
    override fun getAllPlateaux(): Flow<List<Plateau>> = plateauDao.getAllPlateaux()

    override suspend fun getPlateau(id: Int): Plateau {
        var res: Plateau? = null
        plateauDao.getPlateau(id).take(1).collect {
            res = it
        }
        return res!!
    }

    override suspend fun insertPlateau(plateau: Plateau) {
        plateauDao.insert(plateau)
    }
}