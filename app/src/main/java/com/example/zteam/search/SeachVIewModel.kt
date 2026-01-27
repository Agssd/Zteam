package com.example.zteam.search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zteam.data.Game
import com.example.zteam.data.RetrofitInstance
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _games = mutableStateOf<List<Game>>(emptyList())
    val games: State<List<Game>> = _games

    private var searchGameJob : Job? = null
    var query by mutableStateOf("")
    val debounceDurationMillis = 500L

    init {
        fetchGames()
    }

    internal fun fetchGames() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPopularGames()
                _games.value = response.results
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error fetching movies: ${e.message}")
            }
        }
    }

    fun searchGames(query: String) {
        this.query = query
        searchGameJob?.cancel()
        searchGameJob = viewModelScope.launch {
            delay(debounceDurationMillis)
            if (query.isEmpty()) {
                fetchGames()
            } else {
                try {
                    val response = RetrofitInstance.api.searchGames(query)
                    _games.value = response.results
                } catch (e: Exception) {
                    Log.e("GamesViewModel", "Error searching games: ${e.message}")
                }
            }
        }
    }
}