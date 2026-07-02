package com.xiaori.player.domain.usecase

import com.xiaori.player.domain.model.Song
import com.xiaori.player.domain.repository.MusicRepository
import javax.inject.Inject

class GetHomeContentUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(): List<Song> =
        repository.getHomeContent()
}
