package com.example.nothingc.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
fun CalculatorTheme(content: @Composable () -> Unit) {
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