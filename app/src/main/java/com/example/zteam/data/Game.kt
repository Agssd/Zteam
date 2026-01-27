package com.example.zteam.data

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

val genreColors = mapOf(
    "Shooter" to Color(0xFF8B0000),      // темно-красный
    "Adventure" to Color(0xFF556B2F),   // темно-оливковый
    "RPG" to Color(0xFF6A5ACD),         // синий с оттенком фиолетового (SlateBlue)
    "Strategy" to Color(0xFF4682B4),    // стальной синий (SteelBlue)
    "Massive Multiplayer" to Color(0xFF708090),      // серо-голубой (SlateGray)
    "Simulation" to Color(0xFF9ACD32),  // желто-зеленый (YellowGreen)
    "Action" to Color(0xFFCD853F),      // персиковый (Peru)
    "Indie" to Color(0xFFDAA520)       // золотистый (GoldenRod)
)

@Serializable
data class Genre(
    val id: Int,
    val name: String,
    val slug: String,
    @SerialName("image_background") val backgroundImage: String? = null
)

@Serializable
data class Screenshot(
    val id: Int,
    val image: String
)

@Serializable
data class Game(
    val id: Int,
    val name: String,
    val description: String? = null,
    val genres: List<Genre> = emptyList(),
    val released: String? = null,
    val rating: Float = 0f,
    val platforms: List<GamePlatformWrapper> = emptyList(),
    @SerialName("background_image") val backgroundImage: String? = null
)

@Serializable
@Immutable
data class ScreenshotResponse(
    val results: List<Screenshot>
)

@Serializable
@Immutable
data class GameResponse(
    val results: List<Game> = emptyList()
)

@Serializable
@Immutable
data class GamePlatformWrapper(
    val platform: Platform,
    val requirements: Requirements? = null
)

@Serializable
@Immutable
data class Platform(
    val id: Int,
    val name: String
)

@Serializable
@Immutable
data class GenreResponse(
    val results: List<Genre> = emptyList()
)
@Serializable
@Immutable
data class Requirements(
    val minimum: String? = null,
    val recommended: String? = null
)