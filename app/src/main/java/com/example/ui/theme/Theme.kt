package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = AwamiDarkEmerald,
    onPrimary = AwamiDarkBackground,
    primaryContainer = AwamiEmeraldLight,
    onPrimaryContainer = Color.White,
    secondary = AwamiAmberLight,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF4A3314),
    onSecondaryContainer = AwamiAmberLight,
    tertiary = AwamiTealContainer,
    background = AwamiDarkBackground,
    onBackground = AwamiDarkOnSurface,
    surface = AwamiDarkSurface,
    onSurface = AwamiDarkOnSurface,
    surfaceVariant = AwamiDarkSurfaceVariant,
    onSurfaceVariant = AwamiDarkOnSurface.copy(alpha = 0.8f),
    outline = AwamiDarkOutline
)

private val LightColorScheme = lightColorScheme(
    primary = AwamiEmerald,
    onPrimary = Color.White,
    primaryContainer = AwamiEmeraldContainer,
    onPrimaryContainer = AwamiOnEmeraldContainer,
    secondary = AwamiAmber,
    onSecondary = Color.White,
    secondaryContainer = AwamiAmberContainer,
    onSecondaryContainer = AwamiOnAmberContainer,
    tertiary = AwamiTeal,
    background = ParchmentBackground,
    onBackground = ParchmentOnSurface,
    surface = ParchmentSurface,
    onSurface = ParchmentOnSurface,
    surfaceVariant = ParchmentSurfaceVariant,
    onSurfaceVariant = ParchmentOnSurface.copy(alpha = 0.85f),
    outline = ParchmentOutline
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
