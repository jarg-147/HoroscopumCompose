package com.bitbiird.horoscopumcompose.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

// Default

val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

// Black

val black = Color(0xFF000000)

// White

val white = Color(0xFFFFFFFF)

// Light Palette

val winterSpringLilac = Color(0xFFB1B2FF)
val nightSnow = Color(0xFFAAC4FF)
val arcLight = Color(0xFFD2DAFF)
val brilliantWhite = Color(0xFFEEF1FF)

// Dark Palette

val americanBlue = Color(0xFF3F3B6C)
val violetMajesty = Color(0xFF624F82)
val karmaChameleon = Color(0xFF9F73AB)
val blueDam = Color(0xFFA3C7D6)

val Colors.splashBackgroundColor: Color
    get() = if (isLight) {
        winterSpringLilac
    } else {
        americanBlue
    }

val Colors.homeBackgroundColor: Color
    get() = if (isLight) {
        winterSpringLilac
    } else {
        americanBlue
    }

val Colors.homeTitleColor: Color
    get() = if (isLight) {
        americanBlue
    } else {
        winterSpringLilac
    }

val Colors.cardBackgroundColor: Color
    get() = if (isLight) {
        arcLight
    } else {
        karmaChameleon
    }

val Colors.cardShadow: Color
    get() = if (isLight) {
        violetMajesty
    } else {
        arcLight
    }

val Colors.cardTextColor: Color
    get() = if (isLight) {
        americanBlue
    } else {
        winterSpringLilac
    }

val Colors.searchBackgroundColor: Color
    get() = if (isLight) {
        arcLight
    } else {
        karmaChameleon
    }

val Colors.searchTextColor: Color
    get() = if (isLight) {
        americanBlue
    } else {
        winterSpringLilac
    }

val Colors.detailBackgroundColor: Color
    get() = if (isLight) {
        arcLight
    } else {
        karmaChameleon
    }