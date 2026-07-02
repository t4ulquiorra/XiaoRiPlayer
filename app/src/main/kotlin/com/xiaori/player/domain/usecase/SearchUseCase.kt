// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/domain/usecase/SearchUseCase.kt
package com.xiaori.player.domain.usecase

import com.xiaori.player.domain.model.SearchResult
import com.xiaori.player.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    operator fun invoke(query: String): Flow<Result<SearchResult>> =
        repository.search(query)
}
