package com.nawm.android_labs.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nawm.android_labs.domain.Character

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "culture") val culture: String,
    @ColumnInfo(name = "born") val born: String,
    @ColumnInfo(name = "titles") val titles: String,
    @ColumnInfo(name = "aliases") val aliases: String,
    @ColumnInfo(name = "playedBy") val playedBy: String
) {
    companion object {
        fun from(name: String, culture: String, born: String, titles: String, aliases: String, playedBy: String): CharacterEntity {
            return CharacterEntity(
                name = name,
                culture = culture,
                born = born,
                titles = titles,
                aliases = aliases,
                playedBy = playedBy
            )
        }
        fun CharacterEntity.toDomainCharacter(): Character {
            return Character(
                name = this.name,
                culture = this.culture,
                born = this.born,
                titles = this.titles.split(", ").filter { it.isNotBlank() },
                aliases = this.aliases.split(", ").filter { it.isNotBlank() },
                playedBy = this.playedBy.split(", ").filter { it.isNotBlank() }
            )
        }
    }
}