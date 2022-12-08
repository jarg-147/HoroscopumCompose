package com.bitbiird.horoscopumcompose.presentation.detail.bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bitbiird.horoscopumcompose.data.model.HoroscopeResponse
import com.bitbiird.horoscopumcompose.util.constants.Day

@Composable
fun DetailBottom(signHoroscopeData: Map<Day, HoroscopeResponse?>) {

    var currentDay by remember {
        mutableStateOf(Day.TODAY)
    }

    Box(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                ambientColor = Color.Black,
                spotColor = Color.Black
            )
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(MaterialTheme.colors.secondary)
    ) {
        
        Column(
            modifier = Modifier
                .padding(vertical = 24.dp)
        ) {
            
            DaysButtons(currentDay) { daySelected -> currentDay = daySelected }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            HoroscopeData(signHoroscopeData[currentDay])
            
        }
        
    }
}