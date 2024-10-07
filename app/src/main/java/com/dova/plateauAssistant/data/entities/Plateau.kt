package com.dova.plateauAssistant.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plateaux")
data class Plateau(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val index: Int,
    val nbCases: Int,
    val nbLines: Int,
    val values: List<Int>
)
