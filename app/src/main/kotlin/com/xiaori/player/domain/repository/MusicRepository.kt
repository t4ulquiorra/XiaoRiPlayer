// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/domain/repository/MusicRepository.kt
package com.xiaori.player.domain.repository

import com.xiaori.player.domain.model.SearchResult
import com.xiaori.player.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun search(query: String): Flow<Result<SearchResult>>
    fun getHomeContent(): Flow<Result<List<Song>>>
    suspend fun getSongStreamUrl(videoId: String): Result<String>
}
