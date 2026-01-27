package com.example.zteam.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import coil.compose.AsyncImage
import com.example.zteam.data.Game
import com.example.zteam.data.Genre
import com.example.zteam.data.genreColors

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameItemRow(game: Game, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = 6.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            game.backgroundImage?.let { url ->
                AsyncImage(
                    model = url,
                    contentDescription = game.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f),
                                Color.Black.copy(alpha = 0.95f),
                                Color.Black.copy(alpha = 1f)
                            )
                        )
                    )
            )

            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = game.name,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    lineHeight = 24.sp,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .padding(top = 3.dp, bottom = 9.dp, start = 12.dp)
                ){
                    game.genres.take(3).fastForEach {
                        val bgColor = genreColors[it.name] ?: Color.Gray
                        val textColor = bgColor.copy(alpha = 0.25f).compositeOver(Color.White)
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(bgColor)
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .align(Alignment.Center),
                                text = it.name,
                                style = MaterialTheme.typography.titleMedium,
                                lineHeight = 12.sp,
                                color = textColor,
                                fontSize = 12.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//private fun GameItemRowPreview() {
//    MaterialTheme {
//        GameItemRow(
//            game = Game(
//                id = 2,
//                name = "Cock",
//                description = "Dick",
//                playtime = 3,
//                genres = listOf(
//                    Genre(
//                        id = 1,
//                        name = "Action",
//                        slug = "Action",
//                        backgroundImage = "Fgdgdg"
//                    )
//                ),
//                backgroundImage = "gdgdg",
//                released = "12.02.02",
//                rating = 3.23
//            )
//        ) {
//
//        }
//    }
//}