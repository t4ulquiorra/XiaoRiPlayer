package com.xiaori.player.domain.usecase

import com.xiaori.player.domain.model.Song
import com.xiaori.player.domain.repository.MusicRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(query: String): List<Song> =
        repository.search(query)
}
