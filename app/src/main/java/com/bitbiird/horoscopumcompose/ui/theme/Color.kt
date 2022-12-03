package com.bitbiird.horoscopumcompose.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

// Default

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

// Black

val black = Color(0xFF000000)
val black50 = Color(0x80000000)
val illicitDarkness = Color(0xFF00002c)

// White

val white = Color(0xFFFFFFFF)
val compactDiscGrey = Color(0xFFCDCDCD)

// Red

val vineyardAutumn = Color(0xFFF04354)

// Orange

val universityOfTennesseeOrange = Color(0xFFf98500)

// Purple

val magicCarpet = Color(0xFF9186BF)
val swissPlum = Color(0xFF5f4bbc)
val mediumSlateBlue = Color(0xFF8169F0)
val blueGem = Color(0xFF49398F)
val benikakehanaPurple = Color(0xFF595275)
val midnightPurple = Color(0xFF2D033B)
val shadeOfViolet = Color(0xFF810CA8)
val ultravioletOnsible = Color(0xFFC147E9)
val loveCloud = Color(0xFFE5B8F4)


val Colors.splashBackgroundColor: Color
    get() = if (isLight) {
        benikakehanaPurple
    } else {
        illicitDarkness
    }

val Colors.homeButtonColor: Color
    get() = if (isLight) {
        mediumSlateBlue
    } else {
        swissPlum
    }