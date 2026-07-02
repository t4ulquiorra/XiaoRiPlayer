// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/data/datasource/remote/YoutubeMusicRemoteDataSource.kt
package com.xiaori.player.data.datasource.remote

import com.github.t4ulquiorra.innertube.Innertube
import com.github.t4ulquiorra.innertube.models.bodies.SearchBody
import com.github.t4ulquiorra.innertube.models.bodies.BrowseBody
import com.github.t4ulquiorra.innertube.models.responses.SearchResponse
import com.github.t4ulquiorra.innertube.models.responses.BrowseResponse
import com.github.t4ulquiorra.innertube.models.responses.SongResponse
import com.xiaori.player.domain.model.Album
import com.xiaori.player.domain.model.Artist
import com.xiaori.player.domain.model.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class YoutubeMusicRemoteDataSource @Inject constructor(
    private val innertube: Innertube
) {

    suspend fun search(query: String): Result<SearchResponse> = withContext(Dispatchers.IO) {
        runCatching {
            innertube.search(SearchBody(query = query))
        }
    }

    suspend fun getHomeContent(): Result<BrowseResponse> = withContext(Dispatchers.IO) {
        runCatching {
            innertube.browse(BrowseBody(browseId = "FEmusic_home"))
        }
    }

    suspend fun getSongStreamUrl(videoId: String): Result<String> = withContext(Dispatchers.IO) {
        runCatching {
            val songResponse: SongResponse = innertube.song(videoId = videoId)
            songResponse.streamingData?.adaptiveFormats?.firstOrNull()?.url
                ?: error("No stream URL found")
        }
    }
}
