package com.bitbiird.horoscopumcompose.presentation.detail.bottom

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bitbiird.horoscopumcompose.ui.theme.HoroscopumTypography
import com.bitbiird.horoscopumcompose.util.constants.Day

@Composable
fun DaysButtons(currentDay: Day, daySelected: (day: Day) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Day.values().forEach { day ->
            OutlinedButton(
                onClick = {
                    if (currentDay != day) {
                        daySelected(day)
                    }
                },
                modifier = Modifier
                    .height(56.dp)
                    .weight(1f), shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = if (currentDay == day) {
                        MaterialTheme.colors.primaryVariant
                    } else {
                        MaterialTheme.colors.primaryVariant.copy(alpha = 0.2f)
                    }
                )
            ) {

                Text(
                    text = day.dayString,
                    style = HoroscopumTypography.caption,
                    color = MaterialTheme.colors.onSecondary,
                    fontWeight = FontWeight.Bold
                )

            }
        }

    }
}