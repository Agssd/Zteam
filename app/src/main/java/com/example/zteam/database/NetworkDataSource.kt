package com.example.zteam.data.remote

import com.example.zteam.data.GameResponse
import com.example.zteam.data.GenreResponse
import com.example.zteam.data.ZteamApiService
import java.time.LocalDate

class NetworkDataSource(private val api: ZteamApiService) {
    suspend fun getPopularGames(): GameResponse {
        return api.getPopularGames()
    }

    suspend fun getGenres(): GenreResponse {
        return api.getGenres()
    }

    suspend fun getHotNewGames(): GameResponse {
        val today = LocalDate.now()
        val lastYear = today.minusYears(1)
        val dateRange = "${lastYear},${today}"
        return api.getHotNewGames(dates = dateRange, ordering = "-added")
    }
}
