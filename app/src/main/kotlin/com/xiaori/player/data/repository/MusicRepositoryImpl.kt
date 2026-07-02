// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/data/repository/MusicRepositoryImpl.kt
package com.xiaori.player.data.repository

import com.xiaori.player.data.datasource.remote.YoutubeMusicRemoteDataSource
import com.xiaori.player.domain.model.Album
import com.xiaori.player.domain.model.Artist
import com.xiaori.player.domain.model.SearchResult
import com.xiaori.player.domain.model.Song
import com.xiaori.player.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val remoteDataSource: YoutubeMusicRemoteDataSource
) : MusicRepository {

    override fun search(query: String): Flow<Result<SearchResult>> = flow {
        val result = remoteDataSource.search(query)
        emit(result.map { response ->
            SearchResult(
                songs = response.contents?.tabbedSearchResultsRenderer?.tabs
                    ?.firstOrNull()?.tabRenderer?.content?.sectionListRenderer?.contents
                    ?.flatMap { section ->
                        section.musicShelfRenderer?.contents?.mapNotNull { content ->
                            content.musicResponsiveListItemRenderer?.let { item ->
                                Song(
                                    id = item.playlistItemData?.videoId ?: "",
                                    title = item.flexColumns?.firstOrNull()?.musicResponsiveListItemFlexColumnRenderer?.text?.runs?.firstOrNull()?.text ?: "",
                                    artist = item.flexColumns?.getOrNull(1)?.musicResponsiveListItemFlexColumnRenderer?.text?.runs?.firstOrNull()?.text ?: "",
                                    album = item.flexColumns?.getOrNull(2)?.musicResponsiveListItemFlexColumnRenderer?.text?.runs?.firstOrNull()?.text ?: "",
                                    artworkUrl = item.thumbnail?.musicThumbnailRenderer?.thumbnail?.thumbnails?.firstOrNull()?.url ?: "",
                                    durationMs = 0L, // Would parse from runs
                                    streamUrl = "" // Will fetch on-demand
                                )
                            }
                        } ?: emptyList()
                    } ?: emptyList(),
                artists = emptyList(), // Simplified mapping
                albums = emptyList()   // Simplified mapping
            )
        })
    }

    override fun getHomeContent(): Flow<Result<List<Song>>> = flow {
        val result = remoteDataSource.getHomeContent()
        emit(result.map { response ->
            response.contents?.singleColumnBrowseResultsRenderer?.tabs
                ?.firstOrNull()?.tabRenderer?.content?.sectionListRenderer?.contents
                ?.flatMap { section ->
                    section.musicCarouselShelfRenderer?.contents?.mapNotNull { content ->
                        content.musicResponsiveListItemRenderer?.let { item ->
                            Song(
                                id = item.playlistItemData?.videoId ?: "",
                                title = item.flexColumns?.firstOrNull()?.musicResponsiveListItemFlexColumnRenderer?.text?.runs?.firstOrNull()?.text ?: "",
                                artist = item.flexColumns?.getOrNull(1)?.musicResponsiveListItemFlexColumnRenderer?.text?.runs?.firstOrNull()?.text ?: "",
                                album = item.flexColumns?.getOrNull(2)?.musicResponsiveListItemFlexColumnRenderer?.text?.runs?.firstOrNull()?.text ?: "",
                                artworkUrl = item.thumbnail?.musicThumbnailRenderer?.thumbnail?.thumbnails?.firstOrNull()?.url ?: "",
                                durationMs = 0L,
                                streamUrl = ""
                            )
                        }
                    } ?: emptyList()
                } ?: emptyList()
        })
    }

    override suspend fun getSongStreamUrl(videoId: String): Result<String> =
        remoteDataSource.getSongStreamUrl(videoId)
}
