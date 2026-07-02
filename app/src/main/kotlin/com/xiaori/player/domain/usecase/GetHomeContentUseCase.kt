// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/domain/usecase/GetHomeContentUseCase.kt
package com.xiaori.player.domain.usecase

import com.xiaori.player.domain.model.Song
import com.xiaori.player.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeContentUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    operator fun invoke(): Flow<Result<List<Song>>> =
        repository.getHomeContent()
}
