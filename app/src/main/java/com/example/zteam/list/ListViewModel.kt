package com.example.zteam.list

import GameRepository
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zteam.data.Game
import com.example.zteam.data.Genre
import com.example.zteam.data.RetrofitInstance
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate


class ListViewModel(private val database: GameRepository) : ViewModel() {
    private val _games = mutableStateOf<List<Game>>(emptyList())
    val games: State<List<Game>> = _games

    private val _hotNewGames = mutableStateOf<List<Game>>(emptyList())
    val hotNewGames: State<List<Game>> = _hotNewGames

    private val _genres = mutableStateOf<List<Genre>>(emptyList())
    val genres: State<List<Genre>> = _genres

    private var genreGameJob: Job? = null
    val _selectedGenreSlug = mutableStateOf<String?>(null)
    val selectedGenreSlug: State<String?> = _selectedGenreSlug


    init {
        refreshPopularGames()
        refreshHotNewGames()
        refreshGenres()
        fetchGenres()
        fetchPopularGames()
        fetchHotNewGames()
    }

    private fun fetchPopularGames() {
        viewModelScope.launch {
            database.getPopularGames().collectLatest { gamesFromDb ->
                _games.value = gamesFromDb
            }
        }
    }

    private fun refreshPopularGames() {
        // Обновляем данные из сети и сохраняем в базу
        viewModelScope.launch {
            try {
                database.refreshPopularGames()
            } catch (e: Exception) {
                Log.e("ListViewModel", "Failed to refresh popular games: ${e.message}")
            }
        }
    }

    private fun refreshHotNewGames() {
        viewModelScope.launch {
            try {
                database.refreshHotNewGames()
            } catch (e: Exception) {
                Log.e("ListViewModel", "Failed to refresh hot new games: ${e.message}")
            }
        }
    }

    internal fun toggleGenre(slug: String) {
        _selectedGenreSlug.value = if (selectedGenreSlug.value == slug) null else slug
        if (selectedGenreSlug.value != null) {
            fetchGamesByGenre(_selectedGenreSlug.value!!)
        } else {
            refreshPopularGames()
        }
    }

//    internal fun fetchGames() {
//        viewModelScope.launch {
//            try {
//                val response = RetrofitInstance.api.getPopularGames()
//                _games.value = response.results
//            } catch (e: Exception) {
//                Log.e("GameViewModel", "Error fetching movies: ${e.message}")
//            }
//        }
//    }

    internal fun fetchHotNewGames() {
        viewModelScope.launch {
            database.getHotNewGames().collect { hotNewGamesList ->
                _hotNewGames.value = hotNewGamesList
            }
        }
    }

    internal fun refreshGenres() {
        viewModelScope.launch {
            try {
                database.refreshGenres()
            } catch (e: Exception) {
                Log.e("GenreViewModel", "Failed to fetch genres: ${e.message}")
            }
        }
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            database.getGenres().collect { genreList ->
                _genres.value = genreList
            }
        }
    }


    fun fetchGamesByGenre(slug: String) {
        genreGameJob?.cancel()
        genreGameJob = viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getGamesByGenre(slug)
                _games.value = response.results
            } catch (e: Exception) {
                Log.e("ListViewModel", "Error fetching by genre: ${e.message}")
            }
        }
    }
}