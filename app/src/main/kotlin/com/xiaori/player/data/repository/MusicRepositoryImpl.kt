package com.xiaori.player.data.repository

import com.music.innertube.YouTube
import com.music.innertube.models.SongItem
import com.xiaori.player.domain.model.Song
import com.xiaori.player.domain.repository.MusicRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicRepositoryImpl @Inject constructor() : MusicRepository {

    override suspend fun search(query: String): List<Song> {
        return try {
            val result = YouTube.search(query, YouTube.SearchFilter.FILTER_SONG).getOrThrow()
            result.items.filterIsInstance<SongItem>().map { songItem ->
                Song(
                    id = songItem.id,
                    title = songItem.title,
                    artists = songItem.artists.joinToString(", ") { it.name },
                    thumbnailUrl = songItem.thumbnail?.url
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getStreamUrl(videoId: String): String? {
        return try {
            val playerResponse = YouTube.player(videoId = videoId).getOrThrow()
            playerResponse.streamingData?.adaptiveFormats
                ?.filter { it.mimeType.startsWith("audio") }
                ?.maxByOrNull { it.bitrate }
                ?.url
        } catch (e: Exception) {
            null
        }
    }
}
