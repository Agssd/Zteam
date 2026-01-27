package com.example.zteam.search

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onGameClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    val games = viewModel.games.value
    Scaffold(
        topBar = {
            TopBarSection(viewModel, onBack)
        }
    ) { padding ->
        SearchResultsSection(
            games = games,
            paddingValues = padding,
            onGameClick = onGameClick
        )
    }
}
