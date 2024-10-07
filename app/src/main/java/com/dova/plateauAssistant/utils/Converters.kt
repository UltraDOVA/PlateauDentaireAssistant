package com.dova.plateauAssistant.utils

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromList(values: List<Int>): String {
        return values.joinToString(",")
    }

    @TypeConverter
    fun toList(values: String): List<Int> {
        return values.split(",").map { it.toInt() }
    }
}