// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/ui/screens/library/LibraryScreen.kt
package com.xiaori.player.ui.screens.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xiaori.player.ui.theme.Typography

@Composable
fun LibraryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)
    ) {
        Text(
            text = "Library",
            style = Typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        // TODO: Implement playlist grid
    }
}
