package com.example.zteam.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zteam.common.GameItemColumn
import com.example.zteam.data.Game

@Composable
fun TopBarSection(viewModel: SearchViewModel, onBack: () -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
    ) {
        Box(Modifier.fillMaxWidth()) {
            TextField(
                value = viewModel.query,
                onValueChange = { viewModel.searchGames(it) },
                placeholder = { Text("Search games...") },
                textStyle = TextStyle(fontSize = 18.sp),
                leadingIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                trailingIcon = {
                    if (viewModel.query.isNotEmpty()) {
                        IconButton(onClick = { viewModel.searchGames("") }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear"
                            )
                        }
                    }
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }
}

@Composable
fun SearchResultsSection(
    games: List<Game>,
    paddingValues: PaddingValues,
    onGameClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        itemsIndexed(games) { index, game ->
            GameItemColumn(
                game = game,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                onClick = { onGameClick(game.id) }
            )
            if (index < games.lastIndex) {
                Divider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )
            }
        }
    }
}