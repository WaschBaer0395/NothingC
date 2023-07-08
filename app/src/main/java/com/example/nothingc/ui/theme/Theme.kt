package com.example.nothingc.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Creates a complete color definition for the
 * [Material color specification](https://material.io/design/color/the-color-system.html#color-theme-creation)
 * using the default light theme values.
 *
 * @see darkColors
 */
fun lightColors(
    primary: Color = NothingWhite,
    onPrimary: Color = NothingBlack,
    secondary: Color = NothingGrey,
    onSecondary: Color = NothingWhite,
    special: Color = NothingRed,
    onSpecial: Color = NothingWhite,
    background: Color = NothingSilver,
    onBackground: Color = NothingBlack,
    error: Color = NothingRed,
    onError: Color = NothingBlack,
): Colors = Colors(
    primary,
    onPrimary,
    secondary,
    onSecondary,
    background,
    special,
    error,
    onPrimary,
    onSecondary,
    onBackground,
    onSpecial,
    onError,
    true
)

/**
 * Creates a complete color definition for the
 * [Material color specification](https://material.io/design/color/the-color-system.html#color-theme-creation)
 * using the default dark theme values.
 *
 * Note: [secondaryVariant] is typically the same as [secondary] in dark theme since contrast
 * levels are higher, and hence there is less need for a separate secondary color.
 *
 * @see lightColors
 */
fun darkColors(
    primary: Color = NothingGrey,
    onPrimary: Color = NothingWhite,
    secondary: Color = NothingWhite,
    onSecondary: Color = NothingBlack,
    special: Color = NothingRed,
    onSpecial: Color = NothingWhite,
    background: Color = NothingBlack,
    onBackground: Color = NothingWhite,
    error: Color = NothingRed,
    onError: Color = NothingBlack,
): Colors = Colors(
    primary,
    onPrimary,
    secondary,
    onSecondary,
    background,
    special,
    error,
    onPrimary,
    onSecondary,
    onBackground,
    onSpecial,
    onError,
    false
)

private val DarkColorPalette = darkColors(
    background = NothingBlack,
    onBackground = NothingWhite,
    primary = NothingGrey,
    onPrimary = NothingWhite,
    secondary = NothingWhite,
    onSecondary = NothingBlack,
    special = NothingRed,
    onSpecial = NothingWhite
)

private val LightColorPalette = lightColors(
    background = NothingSilver,
    onBackground = NothingBlack,
    primary = NothingWhite,
    onPrimary = NothingBlack,
    secondary = NothingGrey,
    onSecondary = NothingWhite,
    special = NothingRed,
    onSpecial = NothingWhite,
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