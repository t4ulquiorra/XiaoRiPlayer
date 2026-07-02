// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/ui/player/PlayerSheet.kt
package com.xiaori.player.ui.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.xiaori.player.domain.model.Song
import com.xiaori.player.ui.theme.Typography

@Composable
fun PlayerSheet(
    currentSong: Song?,
    isPlaying: Boolean,
    progress: Float,
    onTogglePlayPause: () -> Unit,
    onSeek: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    if (currentSong == null) return

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = currentSong.artworkUrl,
            contentDescription = null,
            modifier = Modifier
                .size(280.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = currentSong.title,
            style = Typography.titleLarge,
            modifier = Modifier.padding(top = 24.dp)
        )
        Text(
            text = currentSong.artist,
            style = Typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        )
        IconButton(
            onClick = onTogglePlayPause,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Icon(
                painter = painterResource(
                    if (isPlaying) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play
                ),
                contentDescription = if (isPlaying) "Pause" else "Play",
                modifier = Modifier.size(64.dp)
            )
        }
    }
}
