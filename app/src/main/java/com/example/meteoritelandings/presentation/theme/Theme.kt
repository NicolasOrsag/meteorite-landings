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

//same as DarkColorScheme for now
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF282828),
    secondary = Color(0xFF7700d9),
    tertiary = Color(0xFFc4c4c4),
    surface = Color(0xFF161616),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.Black,
    onSurface = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF282828),
    secondary = Color(0xFF7700d9),
    tertiary = Color(0xFFc4c4c4),
    surface = Color(0xFF161616),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.Black,
    onSurface = Color.White
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