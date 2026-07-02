// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/playback/PlaybackManager.kt
package com.xiaori.player.playback

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import com.xiaori.player.domain.model.Song
import com.xiaori.player.domain.usecase.GetSongStreamUrlUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaybackManager @Inject constructor(
    private val player: ExoPlayer,
    private val mediaSession: MediaSession,
    private val getSongStreamUrlUseCase: GetSongStreamUrlUseCase
) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun playSong(song: Song) {
        coroutineScope.launch {
            val result = getSongStreamUrlUseCase(song.id)
            result.onSuccess { streamUrl ->
                val mediaItem = MediaItem.Builder()
                    .setUri(Uri.parse(streamUrl))
                    .setMediaId(song.id)
                    .build()
                player.setMediaItem(mediaItem)
                player.prepare()
                player.play()
            }.onFailure {
                // Handle error
            }
        }
    }

    fun pause() {
        player.pause()
    }

    fun resume() {
        player.play()
    }

    fun seekTo(positionMs: Long) {
        player.seekTo(positionMs)
    }

    fun getPlayerState(): PlayerState = PlayerState(
        isPlaying = player.isPlaying,
        currentPositionMs = player.currentPosition,
        durationMs = player.duration
    )

    fun release() {
        player.release()
        mediaSession.release()
    }
}

data class PlayerState(
    val isPlaying: Boolean,
    val currentPositionMs: Long,
    val durationMs: Long
)
