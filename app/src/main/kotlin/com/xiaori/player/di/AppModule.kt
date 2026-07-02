// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/di/AppModule.kt
package com.xiaori.player.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import com.xiaori.player.playback.PlaybackManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideExoPlayer(@ApplicationContext context: Context): ExoPlayer =
        ExoPlayer.Builder(context).build()

    @Provides
    @Singleton
    fun provideMediaSession(
        @ApplicationContext context: Context,
        player: ExoPlayer
    ): MediaSession = MediaSession.Builder(context, player).build()

    @Provides
    @Singleton
    fun providePlaybackManager(
        player: ExoPlayer,
        mediaSession: MediaSession
    ): PlaybackManager = PlaybackManager(player, mediaSession)
}
