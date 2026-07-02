// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/ui/screens/home/HomeScreen.kt
package com.xiaori.player.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.xiaori.player.ui.components.GreetingTopAppBar
import com.xiaori.player.ui.components.SongCard
import com.xiaori.player.ui.theme.Typography

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onSongClick: (com.xiaori.player.domain.model.Song) -> Unit
) {
    val uiState = viewModel.uiState.value

    LaunchedEffect(Unit) {
        // Initial load handled in VM init
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp) // Space for MiniPlayer
    ) {
        GreetingTopAppBar()

        if (uiState.quickPicks.isNotEmpty()) {
            Text(
                text = "Quick Picks",
                style = Typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            LazyRow(
                state = rememberLazyListState(),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(uiState.quickPicks, key = { it.id }) { song ->
                    SongCard(
                        song = song,
                        modifier = Modifier.padding(end = 12.dp),
                        onClick = { onSongClick(song) }
                    )
                }
            }
        }

        if (uiState.trending.isNotEmpty()) {
            Text(
                text = "Trending",
                style = Typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
            LazyRow(
                state = rememberLazyListState(),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(uiState.trending, key = { it.id }) { song ->
                    SongCard(
                        song = song,
                        modifier = Modifier.padding(end = 12.dp),
                        onClick = { onSongClick(song) }
                    )
                }
            }
        }
    }
}
