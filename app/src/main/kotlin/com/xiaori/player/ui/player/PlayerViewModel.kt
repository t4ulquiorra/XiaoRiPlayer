// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/ui/player/PlayerViewModel.kt
package com.xiaori.player.ui.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiaori.player.domain.model.Song
import com.xiaori.player.playback.PlaybackManager
import com.xiaori.player.playback.PlayerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playbackManager: PlaybackManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState = _uiState.asStateFlow()

    fun playSong(song: Song) {
        playbackManager.playSong(song)
        _uiState.value = _uiState.value.copy(
            currentSong = song,
            isPlaying = true
        )
        startProgressUpdates()
    }

    fun togglePlayPause() {
        if (_uiState.value.isPlaying) {
            playbackManager.pause()
            _uiState.value = _uiState.value.copy(isPlaying = false)
        } else {
            playbackManager.resume()
            _uiState.value = _uiState.value.copy(isPlaying = true)
        }
    }

    fun seekTo(positionMs: Long) {
        playbackManager.seekTo(positionMs)
    }

    private fun startProgressUpdates() {
        viewModelScope.launch {
            while (true) {
                kotlinx.coroutines.delay(1000)
                val state = playbackManager.getPlayerState()
                _uiState.value = _uiState.value.copy(
                    playerState = state
                )
            }
        }
    }
}

data class PlayerUiState(
    val currentSong: Song? = null,
    val isPlaying: Boolean = false,
    val playerState: PlayerState = PlayerState(false, 0L, 0L)
)
