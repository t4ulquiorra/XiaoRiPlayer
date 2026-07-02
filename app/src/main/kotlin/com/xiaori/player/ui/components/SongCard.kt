// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/ui/components/SongCard.kt
package com.xiaori.player.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.xiaori.player.domain.model.Song
import com.xiaori.player.ui.theme.Typography

@Composable
fun SongCard(
    song: Song,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(160.dp)
            .clip(RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = com.xiaori.player.ui.theme.SurfaceElevated),
        onClick = onClick
    ) {
        Column {
            AsyncImage(
                model = song.artworkUrl,
                contentDescription = null,
                modifier = Modifier
                    .height(160.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = song.title,
                style = Typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp),
                maxLines = 1
            )
            Text(
                text = song.artist,
                style = Typography.labelMedium,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                maxLines = 1
            )
        }
    }
}
