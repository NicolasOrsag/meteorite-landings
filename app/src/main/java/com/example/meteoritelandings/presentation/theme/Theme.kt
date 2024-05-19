package com.example.meteoritelandings.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1B3A57), // Deep Space Blue
    secondary = Color(0xFF455A64), // Comet Grey
    tertiary = Color(0xFF82B1FF), // Meteor Blue

    background = Color(0xFFE3F2FD), // Light Sky Blue Background
    surface = Color(0xFFFFFFFF), // White Surface
    onPrimary = Color.White, // White text on primary color
    onSecondary = Color.White, // White text on secondary color
    onTertiary = Color.Black, // Black text on tertiary color
    onBackground = Color.Black, // Black text on background
    onSurface = Color.Black // Black text on surface
)


private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF82B1FF), // Meteor Blue
    secondary = Color(0xFF455A64), // Comet Grey
    tertiary = Color(0xFF1B3A57), // Deep Space Blue

    background = Color(0xFF0D1B2A), // Deep Space Background
    surface = Color(0xFF1B263B), // Dark Surface
    onPrimary = Color.Black, // Black text on primary color
    onSecondary = Color.White, // White text on secondary color
    onTertiary = Color.White, // White text on tertiary color
    onBackground = Color.White, // White text on background
    onSurface = Color.White // White text on surface
)


@Composable
fun MeteoriteLandingsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}