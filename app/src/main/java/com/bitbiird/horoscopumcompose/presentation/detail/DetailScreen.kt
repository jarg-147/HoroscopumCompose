package com.bitbiird.horoscopumcompose.presentation.detail

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bitbiird.horoscopumcompose.R
import com.bitbiird.horoscopumcompose.data.model.HoroscopeResponse
import com.bitbiird.horoscopumcompose.data.network.model.ErrorType
import com.bitbiird.horoscopumcompose.data.network.model.NetworkState
import com.bitbiird.horoscopumcompose.ui.theme.HoroscopumTypography
import com.bitbiird.horoscopumcompose.util.constants.Day
import com.bitbiird.horoscopumcompose.util.enums.HoroscopeSigns
import kotlinx.coroutines.delay

@Composable
fun DetailScreen(navController: NavController, signId: Int, viewModel: DetailScreenViewModel = hiltViewModel()) {

    val sign = HoroscopeSigns.values().find { sign -> sign.id == signId }

    val dataState by viewModel.horoscopeDataState.collectAsState()

    if (sign != null) {
        val signName = stringResource(id = sign.signName).lowercase()

        LaunchedEffect(key1 = true, block = {
            viewModel.init(signName)
        })

        var showDetailHeader by remember {
            mutableStateOf(false)
        }

        var showDetailData by remember {
            mutableStateOf(false)
        }

        when (val response = dataState) {
            is NetworkState.Loading -> Loading()
            is NetworkState.Success -> {
                LaunchedEffect(key1 = true) {
                    delay(200)
                    showDetailHeader = true
                    delay(300)
                    showDetailData = true
                }

                BackHandler {
                    showDetailData = false
                    showDetailHeader = false
                    navController.popBackStack()
                }

                Detail(sign = sign, signHoroscopeData = response.data, showDetailHeader, showDetailData) {
                    showDetailData = false
                    showDetailHeader = false
                    navController.popBackStack()
                }

            }
            is NetworkState.Error -> Error(response.error) {
                viewModel.init(signName)
            }
        }
    }


}

@Composable
fun Detail(
    sign: HoroscopeSigns,
    signHoroscopeData: Map<Day, HoroscopeResponse?>,
    showDetailHeader: Boolean,
    showDetailData: Boolean,
    onBackPressed: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TopAppBar(elevation = 0.dp, backgroundColor = Color.Transparent) {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, contentDescription = "Back icon", tint = MaterialTheme.colors.onPrimary
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(
            visible = showDetailHeader,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            DetailHeader(sign)
        }
        Spacer(modifier = Modifier.height(32.dp))
        AnimatedVisibility(
            visible = showDetailData,
            enter = fadeIn() + slideInVertically { it / 2 },
            exit = fadeOut() + slideOutVertically { it / 2 }
        ) {
            DetailBottom(signHoroscopeData)
        }
    }
}

@Composable
private fun DetailHeader(sign: HoroscopeSigns) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = sign.signIcon), contentDescription = "Sign icon", modifier = Modifier.size(82.dp)
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
            style = HoroscopumTypography.caption,
            color = MaterialTheme.colors.onSurface,
            fontStyle = FontStyle.Italic
        )
    }
}

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
        Column {
            DaysButtons(currentDay) { daySelected -> currentDay = daySelected }
            HoroscopeData(signHoroscopeData[currentDay])
        }
    }
}

@Composable
private fun DaysButtons(currentDay: Day, daySelected: (day: Day) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .padding(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Day.values().forEach { day ->
            OutlinedButton(
                onClick = {
                    if (currentDay != day) {
                        daySelected(day)
                    }
                }, modifier = Modifier
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

@Composable
fun HoroscopeData(horoscopeResponse: HoroscopeResponse?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
            .padding(horizontal = 16.dp)
    ) {

        val sharedModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)

        Row(
            modifier = sharedModifier, horizontalArrangement = Arrangement.spacedBy(16.dp)
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
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = sharedModifier, horizontalArrangement = Arrangement.spacedBy(16.dp)
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
        Spacer(modifier = Modifier.height(24.dp))
        InfoItem(
            header = stringResource(id = R.string.description),
            text = horoscopeResponse?.description ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            isDescription = true
        )
    }
}

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

@Composable
fun Loading() {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F, targetValue = 360F, animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing, delayMillis = 0), repeatMode = RepeatMode.Restart
        )
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
            .background(Color.Black.copy(alpha = 0.3f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_moon), contentDescription = "Loading icon", modifier = Modifier.rotate(angle)
        )
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        Text(
            text = stringResource(id = R.string.loading_text),
            style = HoroscopumTypography.h2,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun Error(error: ErrorType, onRetryButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_fire), contentDescription = "Error image", modifier = Modifier.size(100.dp)
        )
        Text(
            text = stringResource(id = R.string.error_detail_title),
            style = HoroscopumTypography.h2,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(
                id = if (error == ErrorType.API_ERROR) {
                    R.string.error_detail_api_text
                } else {
                    R.string.error_detail_internet_text
                }
            ),
            style = HoroscopumTypography.h4,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onRetryButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text(text = stringResource(id = R.string.error_detail_retry_button), color = MaterialTheme.colors.onPrimary)
        }
        Button(
            onClick = onRetryButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .padding(horizontal = 24.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text(text = stringResource(id = R.string.error_detail_ok_button), color = MaterialTheme.colors.onPrimary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    Detail(sign = HoroscopeSigns.Cancer, signHoroscopeData = mapOf(), showDetailData = true, showDetailHeader = true) {}
}

@Preview(showBackground = true)
@Composable
fun DetailDataPreview() {
    HoroscopeData(
        horoscopeResponse = HoroscopeResponse(
            compatibility = "Compatibility text",
            description = "Long description text",
            currentDate = "Current date text",
            luckyNumber = "Lucky number text",
            luckyTime = "Lucky number text"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    Loading()
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    Error(ErrorType.API_ERROR) {}
}