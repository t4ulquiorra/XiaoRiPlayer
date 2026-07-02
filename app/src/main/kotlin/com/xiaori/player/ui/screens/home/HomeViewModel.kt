// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/ui/screens/home/HomeViewModel.kt
package com.xiaori.player.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiaori.player.domain.model.Song
import com.xiaori.player.domain.usecase.GetHomeContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeContentUseCase: GetHomeContentUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadHomeContent()
    }

    private fun loadHomeContent() {
        viewModelScope.launch {
            getHomeContentUseCase().collectLatest { result ->
                result.onSuccess { songs ->
                    _uiState.value = _uiState.value.copy(
                        quickPicks = songs.take(8),
                        trending = songs.take(20),
                        isLoading = false
                    )
                }.onFailure {
                    _uiState.value = _uiState.value.copy(
                        error = it.message,
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class HomeUiState(
    val quickPicks: List<Song> = emptyList(),
    val trending: List<Song> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)
