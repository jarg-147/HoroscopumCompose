package com.bitbiird.horoscopumcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = winterSpringLilac,
    primaryVariant = nightSnow,
    onPrimary = brilliantWhite,
    surface = nightSnow,
    onSurface = brilliantWhite,
    secondary = arcLight,
    onSecondary = violetMajesty,
)

private val DarkColorPalette = darkColors(
    primary = americanBlue,
    primaryVariant = violetMajesty,
    onPrimary = greyArea,
    surface = violetMajesty,
    onSurface = greyArea,
    secondary = americanBlue,
    onSecondary = greyArea,
)


@Composable
fun HoroscopumComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = HoroscopumTypography,
        shapes = Shapes,
        content = content
    )
}