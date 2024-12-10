package com.nawm.android_labs.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nawm.android_labs.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character WHERE id BETWEEN :startId AND :endId")
    fun getCharactersInRange(startId: Int, endId: Int): List<CharacterEntity>

    @Query("SELECT * FROM character WHERE id = :id LIMIT 1")
    fun getCharacterById(id: Int): CharacterEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM character")
    fun getAllCharacters(): LiveData<List<CharacterEntity>>

    @Query("DELETE FROM character")
    fun deleteAllCharacters()

    @Query("SELECT * FROM character")
    fun getAllCharactersFlow() : Flow<List<CharacterEntity>>
}