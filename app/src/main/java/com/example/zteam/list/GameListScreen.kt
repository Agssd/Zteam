package com.example.zteam.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@Composable
fun GameListScreen(
    viewModel: ListViewModel,
    onGameClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    onInfoClick: () -> Unit

) {

    val popularGames = viewModel.games.value
    val hotNewGames = viewModel.hotNewGames.value
    val genres = viewModel.genres.value
    val selectedGenreSlug = viewModel.selectedGenreSlug.value

    var showInfoDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SearchTopBar(
                onSearchClick = onSearchClick,
                onInfoClick = { showInfoDialog = true }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            item {
                PopularGamesSection(hotNewGames, onGameClick)
            }

            item {
                CategorySection(viewModel, genres, selectedGenreSlug)

            }

            item {
                GameListSection(popularGames, onGameClick)
            }
        }
        if (showInfoDialog) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { showInfoDialog = false },
                confirmButton = {
                    androidx.compose.material3.TextButton(onClick = { showInfoDialog = false }) {
                        androidx.compose.material3.Text("ОК")
                    }
                },
                title = { androidx.compose.material3.Text("Информация") },
                text = {
                    androidx.compose.material3.Text("Батюта Николай Сергеевич\nГруппа: ЕТ-411")
                }
            )
        }
    }
}