package com.xiaori.player.domain.repository

import com.xiaori.player.domain.model.Song

interface MusicRepository {
    suspend fun search(query: String): List<Song>
    suspend fun getHomeContent(): List<Song>
    suspend fun getStreamUrl(videoId: String): String?
}
