// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/MainActivity.kt
package com.xiaori.player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xiaori.player.ui.screens.home.HomeScreen
import com.xiaori.player.ui.screens.library.LibraryScreen
import com.xiaori.player.ui.screens.search.SearchScreen
import com.xiaori.player.ui.theme.XiaoRiPlayerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XiaoRiPlayerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = com.xiaori.player.ui.theme.Background
                ) {
                    val navController = rememberNavController()
                    var currentSong by remember { mutableStateOf<com.xiaori.player.domain.model.Song?>(null) }
                    var isPlaying by remember { mutableStateOf(false) }

                    val playerViewModel: com.xiaori.player.ui.player.PlayerViewModel = hiltViewModel()

                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen(
                                onSongClick = { song ->
                                    currentSong = song
                                    playerViewModel.playSong(song)
                                }
                            )
                        }
                        composable("search") {
                            SearchScreen(
                                onSongClick = { song ->
                                    currentSong = song
                                    playerViewModel.playSong(song)
                                }
                            )
                        }
                        composable("library") {
                            LibraryScreen()
                        }
                    }

                    // MiniPlayer would be placed here overlaying NavHost
                    // PlayerSheet would be shown as BottomSheet / Dialog
                }
            }
        }
    }
}
