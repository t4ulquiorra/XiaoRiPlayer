// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/domain/model/Song.kt
package com.xiaori.player.domain.model

data class Song(
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val artworkUrl: String,
    val durationMs: Long,
    val streamUrl: String
)
