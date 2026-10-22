package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = StudioAmber,
    onPrimary = Color(0xFF0F0E05),
    primaryContainer = StudioAmber.copy(alpha = 0.15f),
    onPrimaryContainer = StudioAmber,
    secondary = StudioPink,
    onSecondary = Color.White,
    secondaryContainer = StudioPink.copy(alpha = 0.15f),
    onSecondaryContainer = StudioPink,
    tertiary = StudioPurple,
    onTertiary = Color.White,
    tertiaryContainer = StudioPurple.copy(alpha = 0.15f),
    onTertiaryContainer = StudioPurple,
    background = StudioBackground,
    onBackground = StudioTextPrimary,
    surface = StudioSurface,
    onSurface = StudioTextPrimary,
    surfaceVariant = StudioSurfaceVariant,
    onSurfaceVariant = StudioTextSecondary,
    outline = StudioBorder,
    outlineVariant = StudioBorderSubtle
)

@Composable
fun CreativeDesignTheme(
    darkTheme: Boolean = true, // Force sleek studio dark mode
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
