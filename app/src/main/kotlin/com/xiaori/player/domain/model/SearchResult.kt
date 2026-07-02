// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/domain/model/SearchResult.kt
package com.xiaori.player.domain.model

data class SearchResult(
    val songs: List<Song>,
    val artists: List<Artist>,
    val albums: List<Album>
)

data class Artist(
    val id: String,
    val name: String,
    val thumbnailUrl: String
)

data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val artworkUrl: String
)
