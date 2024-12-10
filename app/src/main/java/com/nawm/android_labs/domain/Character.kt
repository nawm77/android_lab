package com.nawm.android_labs.domain

import com.nawm.android_labs.entity.CharacterEntity
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val name: String,
    val culture: String,
    val born: String,
    val titles: List<String>,
    val aliases: List<String>,
    val playedBy: List<String>
) {
    companion object{
        fun Character.toEntity(id: Int): CharacterEntity {
            return CharacterEntity(
                id = id,
                name = this.name,
                culture = this.culture,
                born = this.born,
                titles = this.titles.joinToString(", "),
                aliases = this.aliases.joinToString(", "),
                playedBy = this.playedBy.joinToString(", ")
            )
        }
    }
}