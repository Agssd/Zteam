package com.example.zteam.database

import androidx.room.TypeConverter

class GenreListConverter {
    @TypeConverter
    fun fromGenreList(genres: List<String>): String = genres.joinToString(",")

    @TypeConverter
    fun toGenreList(data: String): List<String> =
        if (data.isBlank()) emptyList() else data.split(",")
}