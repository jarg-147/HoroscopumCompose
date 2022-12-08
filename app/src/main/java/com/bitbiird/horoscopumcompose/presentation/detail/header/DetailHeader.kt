package com.bitbiird.horoscopumcompose.presentation.detail.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bitbiird.horoscopumcompose.ui.theme.HoroscopumTypography
import com.bitbiird.horoscopumcompose.util.enums.HoroscopeSigns

@Composable
fun DetailHeader(sign: HoroscopeSigns) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = sign.signIcon),
            contentDescription = "Sign icon",
            modifier = Modifier.size(82.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = sign.signName),
            style = HoroscopumTypography.h2,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = sign.signDate),
            style = HoroscopumTypography.h4,
            color = MaterialTheme.colors.onSurface,
            fontStyle = FontStyle.Italic
        )

    }
}