package com.bitbiird.horoscopumcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bitbiird.horoscopumcompose.R

val HoroscopumFonts = FontFamily(
    Font(R.font.bronovaregular, FontWeight.Normal),
    Font(R.font.bronovabold, FontWeight.Bold)
)

val HoroscopumTypography = Typography(
    defaultFontFamily = HoroscopumFonts,
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = Color.White
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.White
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        color = Color.White
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White
    ),
    button = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        color = Color.White
    )
)