package com.example.zteam.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zteam.R
import com.example.zteam.common.GameItemColumn
import com.example.zteam.common.GameItemRow
import com.example.zteam.common.GenreItem
import com.example.zteam.data.Game
import com.example.zteam.data.Genre

@Composable
fun SearchTopBar(
    onSearchClick: () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentPadding = PaddingValues(horizontal = 10.dp),
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 4.dp)
            )

            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun PopularGamesSection(games: List<Game>, onGameClick: (Int) -> Unit) {
    Text(
        text = "Pupular Games",
        fontSize = 32.sp,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(games) { game ->
            GameItemRow(
                game = game,
                modifier = Modifier.width(240.dp),
                onClick = { onGameClick(game.id) }
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun CategorySection(viewModel: ListViewModel, genres: List<Genre>, selectedGenreSlug: String?) {
    Text(
        text = "Categories",
        style = androidx.compose.material.MaterialTheme.typography.h6,
        fontSize = 32.sp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        items(genres) { genre ->
            GenreItem(
                genre = genre,
                modifier = Modifier
                    .width(124.dp)
                    .border(
                        width = if (selectedGenreSlug == genre.slug) 3.dp else 0.dp,
                        color = if (selectedGenreSlug == genre.slug) Color(0xFF4DB6AC) else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    ),
                onClick = {
                    viewModel.toggleGenre(genre.slug)
                }
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun GameListSection(games: List<Game>, onGameClick: (Int) -> Unit) {
    Text(
        text = "Games",
        style = MaterialTheme.typography.titleLarge,
        fontSize = 32.sp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    games.forEachIndexed { index, game ->
        GameItemColumn(
            game = game,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
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
