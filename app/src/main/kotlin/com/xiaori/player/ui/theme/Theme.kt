// FILE: XiaoRiPlayer/app/src/main/kotlin/com/xiaori/player/ui/theme/Theme.kt
package com.xiaori.player.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = AccentDynamic,
    onPrimary = TextPrimary,
    surface = Surface,
    onSurface = TextPrimary,
    background = Background,
    onBackground = TextPrimary,
    surfaceVariant = SurfaceElevated,
    onSurfaceVariant = TextSecondary
)

@Composable
fun XiaoRiPlayerTheme(
    dynamicAccent: Color? = null,
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme.copy(
        primary = dynamicAccent ?: AccentDynamic
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
