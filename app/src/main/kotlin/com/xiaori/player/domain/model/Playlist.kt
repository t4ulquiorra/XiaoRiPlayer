// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/domain/model/Playlist.kt
package com.xiaori.player.domain.model

data class Playlist(
    val id: String,
    val title: String,
    val artworkUrl: String,
    val songCount: Int
)
