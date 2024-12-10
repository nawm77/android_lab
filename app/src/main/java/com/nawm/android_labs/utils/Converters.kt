package com.nawm.android_labs.utils

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromStringToList(data: String): List<String> {
        return data.split(",").map { it.trim() }
    }
}