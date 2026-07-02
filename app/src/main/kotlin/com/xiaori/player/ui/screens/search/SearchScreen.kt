// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/ui/screens/search/SearchScreen.kt
package com.xiaori.player.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.xiaori.player.ui.components.SongCard
import com.xiaori.player.ui.theme.Typography

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onSongClick: (com.xiaori.player.domain.model.Song) -> Unit
) {
    val uiState = viewModel.uiState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)
    ) {
        OutlinedTextField(
            value = uiState.searchResult?.let { "" } ?: "",
            onValueChange = viewModel::onSearchQueryChanged,
            placeholder = { Text("Search songs, artists, albums") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        if (uiState.isLoading) {
            Text("Searching...")
        } else {
            uiState.searchResult?.songs?.let { songs ->
                Text(
                    text = "Songs",
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    items(songs, key = { it.id }) { song ->
                        SongCard(
                            song = song,
                            modifier = Modifier.padding(vertical = 4.dp),
                            onClick = { onSongClick(song) }
                        )
                    }
                }
            }
        }
    }
}
