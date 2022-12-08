package com.bitbiird.horoscopumcompose.presentation.detail.bottom

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bitbiird.horoscopumcompose.R
import com.bitbiird.horoscopumcompose.data.model.HoroscopeResponse

@Composable
fun HoroscopeData(horoscopeResponse: HoroscopeResponse?) {

    val sharedModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            modifier = sharedModifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            InfoItem(
                header = stringResource(id = R.string.current_date),
                text = horoscopeResponse?.currentDate ?: "",
                modifier = Modifier.weight(1f)
            )

            InfoItem(
                header = stringResource(id = R.string.compatibility),
                text = horoscopeResponse?.compatibility ?: "",
                modifier = Modifier.weight(1f)
            )

        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = sharedModifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            InfoItem(
                header = stringResource(id = R.string.lucky_time),
                text = horoscopeResponse?.luckyTime ?: "",
                modifier = Modifier.weight(1f)
            )

            InfoItem(
                header = stringResource(id = R.string.lucky_number),
                text = horoscopeResponse?.luckyNumber ?: "",
                modifier = Modifier.weight(1f)
            )

        }

        Spacer(modifier = Modifier.height(32.dp))

        InfoItem(
            header = stringResource(id = R.string.description),
            text = horoscopeResponse?.description ?: "",
            modifier = sharedModifier,
            isDescription = true
        )
    }
}