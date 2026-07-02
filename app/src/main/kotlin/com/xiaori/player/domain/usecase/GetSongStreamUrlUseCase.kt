// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/domain/usecase/GetSongStreamUrlUseCase.kt
package com.xiaori.player.domain.usecase

import com.xiaori.player.domain.repository.MusicRepository
import javax.inject.Inject

class GetSongStreamUrlUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(videoId: String): Result<String> =
        repository.getSongStreamUrl(videoId)
}
