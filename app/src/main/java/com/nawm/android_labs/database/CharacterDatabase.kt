package com.nawm.android_labs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nawm.android_labs.dao.CharacterDao
import com.nawm.android_labs.entity.CharacterEntity
import com.nawm.android_labs.utils.Converters

@TypeConverters(Converters::class)
@Database(entities = [CharacterEntity::class], version = 2, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}