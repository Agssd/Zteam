package com.example.zteam.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.zteam.data.Genre

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val slug: String,
    val backgroundImage: String?
) {
    fun toGenre(): Genre {
        return Genre(
            id = id,
            name = name,
            slug = slug,
            backgroundImage = backgroundImage
        )
    }

    companion object {
        fun fromGenre(genre: Genre): GenreEntity {
            return GenreEntity(
                id = genre.id,
                name = genre.name,
                slug = genre.slug,
                backgroundImage = genre.backgroundImage
            )
        }
    }
}
