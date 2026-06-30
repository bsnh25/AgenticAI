package com.learnagentic.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Avatar Design System — Primary Palette
private val AvatarPurple = Color(0xFF7C4DFF)
private val AvatarPurpleVariant = Color(0xFF5E35B1)
private val AvatarSurface = Color(0xFF1A1A2E)
private val AvatarBackground = Color(0xFF0F0F23)
private val AvatarOnBackground = Color(0xFFE8E8F0)

private val DarkColorScheme = darkColorScheme(
    primary = AvatarPurple,
    secondary = AvatarPurpleVariant,
    background = AvatarBackground,
    surface = AvatarSurface,
    onBackground = AvatarOnBackground,
    onSurface = AvatarOnBackground,
)

private val LightColorScheme = lightColorScheme(
    primary = AvatarPurple,
    secondary = AvatarPurpleVariant,
)

/**
 * LearnAgenticTheme — the root theme for all composables.
 * Wraps Material 3 with the Avatar Design System color palette.
 * Typography uses the Interphases font (to be wired by Frontend agent).
 */
@Composable
fun LearnAgenticTheme(
    darkTheme: Boolean = true, // Default to dark as per Avatar design spec
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = LearnAgenticTypography,
        content = content
    )
}
