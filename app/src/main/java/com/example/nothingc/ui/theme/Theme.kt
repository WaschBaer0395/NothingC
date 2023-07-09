package com.example.nothingc.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    background = NothingBlack,
    onBackground = NothingWhite,
    primary = NothingGrey,
    onPrimary = NothingWhite,
    secondary = NothingWhite,
    onSecondary = NothingBlack,
    error = NothingRed,
    onError = NothingWhite
)

private val LightColorPalette = lightColors(
    background = NothingSilver,
    onBackground = NothingBlack,
    primary = NothingWhite,
    onPrimary = NothingBlack,
    secondary = NothingGrey,
    onSecondary = NothingWhite,
    error = NothingRed,
    onError = NothingWhite,
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(color = colors.background)
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun CalculatorTheme(
    useDarkTheme: Boolean =  isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    // color scheme selection code
    val colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette

    // Add primary status bar color from chosen color scheme.
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()
            WindowCompat
                .getInsetsController(window, view)
                .isAppearanceLightStatusBars = !useDarkTheme
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}