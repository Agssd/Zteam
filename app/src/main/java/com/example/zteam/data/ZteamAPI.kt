package com.example.zteam.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ZteamApiService {
    @GET("games")
    suspend fun getPopularGames(
        @Query("ordering") ordering: String = "-ratings_count,-rating"
    ): GameResponse

    @GET("games")
    suspend fun searchGames(
        @Query("search") query: String
    ): GameResponse

    @GET("games")
    suspend fun getGamesByGenre(
        @Query("genres") genreSlug: String
    ): GameResponse

    @GET("games")
    suspend fun getHotNewGames(
        @Query("dates") dates: String,
        @Query("ordering") ordering: String = "-added"
    ): GameResponse

    @GET("games/{id}")
    suspend fun infoGame(
        @Path("id") id: Int
    ): Game

    @GET("games/{id}/screenshots")
    suspend fun getGameScreenshots(
        @Path("id") gameId: Int
    ): ScreenshotResponse

    @GET("genres")
    suspend fun getGenres(
    ):GenreResponse
}