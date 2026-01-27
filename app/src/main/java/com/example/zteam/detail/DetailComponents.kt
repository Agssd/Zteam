package com.example.zteam.detail

import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import com.example.zteam.data.Genre
import com.example.zteam.data.Requirements
import com.example.zteam.data.genreColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSection(onBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "View Game",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerSection(images: List<String>) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        pageSpacing = 12.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { page ->
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(images[page]),
                contentDescription = "Game Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp, top = 4.dp, start = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) Color(0xFF4DB6AC) else Color(
                    0xFF4DB6AC
                ).copy(alpha = 0.5f)
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(12.dp)
            )
        }

    }
}

@Composable
fun GameInfoSection(name: String, rating: Float, released: String?) {
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        androidx.compose.material3.Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            androidx.compose.material3.Text(
                text = "$rating",
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Date release: ${released ?: "Unknown release date"}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

    Spacer(modifier = Modifier.height(12.dp))
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenresFlowSection(genres: List<Genre>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {

        genres.fastForEach {
            val bgColor = genreColors[it.name] ?: Color.Gray
            val textColor = bgColor.copy(alpha = 0.25f).compositeOver(Color.White)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(bgColor)
            ) {
                androidx.compose.material3.Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.Center),
                    text = it.name,
                    style = MaterialTheme.typography.titleMedium,
                    lineHeight = 12.sp,
                    color = textColor,
                    fontSize = 16.sp,
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))

    Divider(color = MaterialTheme.colorScheme.outlineVariant, thickness = 1.dp)

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun DescriptionSection(description: String?) {
    Text(
        text = "Description",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(bottom = 8.dp),
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    AndroidView(
        factory = { context ->
            TextView(context).apply {
                text = Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY)
                textSize = 16f
                setTextColor(android.graphics.Color.WHITE)
                setLineSpacing(1.2f, 1.2f)
                movementMethod =
                    LinkMovementMethod.getInstance()
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun SystemRequirementsSection(pcPlatform: Requirements?) {
    if (!pcPlatform?.minimum.isNullOrBlank() && !pcPlatform?.recommended.isNullOrBlank()) {
        Divider(color = MaterialTheme.colorScheme.outlineVariant, thickness = 1.dp)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "System Requirements",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        pcPlatform?.minimum?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        pcPlatform?.recommended?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}