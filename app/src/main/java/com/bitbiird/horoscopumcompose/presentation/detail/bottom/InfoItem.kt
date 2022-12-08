package com.bitbiird.horoscopumcompose.presentation.detail.bottom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bitbiird.horoscopumcompose.ui.theme.HoroscopumTypography

@Composable
fun InfoItem(header: String, text: String, modifier: Modifier, isDescription: Boolean = false) {
    if (text.isNotEmpty()) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = header, style = HoroscopumTypography.h3,
                color = MaterialTheme.colors.onSecondary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(if (isDescription) 16.dp else 8.dp))

            Text(
                text = text, style = HoroscopumTypography.h4,
                color = MaterialTheme.colors.onSecondary,
                lineHeight = if (isDescription) 22.sp else TextUnit.Unspecified,
                textAlign = TextAlign.Center
            )

        }
    }
}