package com.example.zteam.detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zteam.data.Game
import com.example.zteam.data.RetrofitInstance
import com.example.zteam.data.Screenshot
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val _game = mutableStateOf<Game?>(null)
    val game: State<Game?> = _game

    private val _screenshots = mutableStateOf<List<Screenshot>>(emptyList())
    val screenshot: State<List<Screenshot>> = _screenshots

    fun fetchMovie(gameID : Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.infoGame(gameID)
                _game.value = response
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error fetching movie info: ${e.message}")
            }
        }
    }

    fun fetchScreenshots(gameId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getGameScreenshots(gameId)
                _screenshots.value = response.results
            } catch (e: Exception) {
                Log.e("DetailViewModel", "Error loading screenshots", e)
            }
        }
    }
}