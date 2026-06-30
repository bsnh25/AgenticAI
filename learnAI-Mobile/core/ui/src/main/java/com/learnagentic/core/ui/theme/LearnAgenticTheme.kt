package com.learnagentic.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Deep Dark Mode Palette
private val DarkBackground = Color(0xFF0B0D17)
private val DarkSurface = Color(0xFF151828)
private val DarkSurfaceVariant = Color(0xFF23273D)
private val DarkOnBackground = Color(0xFFF1F5F9)
private val DarkPrimary = Color(0xFF8B5CF6) // Neon Violet
private val DarkSecondary = Color(0xFF06B6D4) // Electric Cyan
private val DarkError = Color(0xFFFB7185) // Coral Pink

// Light Mode Palette
private val LightBackground = Color(0xFFF8FAFC)
private val LightSurface = Color(0xFFFFFFFF)
private val LightSurfaceVariant = Color(0xFFE2E8F0)
private val LightOnBackground = Color(0xFF0F172A)
private val LightPrimary = Color(0xFF6D28D9) // Deep Royal Purple
private val LightSecondary = Color(0xFF0369A1) // Ocean Blue
private val LightError = Color(0xFFE11D48) // Rose Red

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    background = DarkBackground,
    surface = DarkSurface,
    surfaceVariant = DarkSurfaceVariant,
    onBackground = DarkOnBackground,
    onSurface = DarkOnBackground,
    onSurfaceVariant = Color(0xFFCBD5E1),
    error = DarkError,
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = LightSecondary,
    background = LightBackground,
    surface = LightSurface,
    surfaceVariant = LightSurfaceVariant,
    onBackground = LightOnBackground,
    onSurface = LightOnBackground,
    onSurfaceVariant = Color(0xFF475569),
    error = LightError,
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
        shapes = LearnAgenticShapes,
        content = content
    )
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun LearnAgenticThemePreviewLight() {
    LearnAgenticTheme(darkTheme = false) {
        androidx.compose.foundation.layout.Box(
            modifier = androidx.compose.ui.Modifier.background(MaterialTheme.colorScheme.background).padding(16.dp)
        ) {
            androidx.compose.material3.Text(
                text = "Light Theme - Outfit & Inter",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun LearnAgenticThemePreviewDark() {
    LearnAgenticTheme(darkTheme = true) {
        androidx.compose.foundation.layout.Box(
            modifier = androidx.compose.ui.Modifier.background(MaterialTheme.colorScheme.background).padding(16.dp)
        ) {
            androidx.compose.material3.Text(
                text = "Dark Theme - Neon Accent",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

