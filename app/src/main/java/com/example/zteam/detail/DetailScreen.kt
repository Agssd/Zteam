package com.example.zteam.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(
    gameId: Int,
    viewModel: DetailViewModel,
    onBack: () -> Unit
) {
    LaunchedEffect(gameId) {
        viewModel.fetchMovie(gameId)
        viewModel.fetchScreenshots(gameId)
    }

    val game = viewModel.game.value
    val pcPlatform = game?.platforms?.find { it.platform.name == "PC" }?.requirements

    Scaffold(
        topBar = {
            TopBarSection(onBack)
        }
    ) { paddingValues ->
        if (game == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            val allImages = remember(game) {
                listOfNotNull(game.backgroundImage!!) + viewModel.screenshot.value.map { it.image }
            }
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(vertical = 12.dp)
            ) {
                HorizontalPagerSection(allImages)
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    GameInfoSection(game.name, game.rating, game.released)

                    GenresFlowSection(game.genres)

                    DescriptionSection(game.description)

                    SystemRequirementsSection(pcPlatform)
                }
            }
        }
    }
}


