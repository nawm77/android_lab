package com.nawm.android_labs

import android.app.Application
import androidx.room.Room
import com.nawm.android_labs.database.CharacterDatabase

class App : Application() {
    private lateinit var db: CharacterDatabase

    override fun onCreate() {
        super.onCreate()

        this.db = Room.databaseBuilder(
            this,
            CharacterDatabase::class.java, "characterentity"
        ).build()
    }

    fun getDb(): CharacterDatabase {
        return db
    }
}