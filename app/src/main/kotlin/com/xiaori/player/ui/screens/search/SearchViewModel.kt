// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/ui/screens/search/SearchViewModel.kt
package com.xiaori.player.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiaori.player.domain.model.SearchResult
import com.xiaori.player.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")

    init {
        _searchQuery
            .debounce(300)
            .filter { it.length >= 2 }
            .onEach { query ->
                if (query.isNotEmpty()) {
                    performSearch(query)
                } else {
                    _uiState.value = SearchUiState()
                }
            }
            .launchIn(viewModelScope)
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    private fun performSearch(query: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            searchUseCase(query).collectLatest { result ->
                result.onSuccess { searchResult ->
                    _uiState.value = _uiState.value.copy(
                        searchResult = searchResult,
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

data class SearchUiState(
    val searchResult: SearchResult? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
