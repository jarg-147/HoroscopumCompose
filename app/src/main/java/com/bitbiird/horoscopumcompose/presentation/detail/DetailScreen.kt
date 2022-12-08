package com.bitbiird.horoscopumcompose.presentation.detail

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bitbiird.horoscopumcompose.R
import com.bitbiird.horoscopumcompose.data.model.HoroscopeResponse
import com.bitbiird.horoscopumcompose.data.network.model.ErrorType
import com.bitbiird.horoscopumcompose.data.network.model.NetworkState
import com.bitbiird.horoscopumcompose.presentation.detail.bottom.DetailBottom
import com.bitbiird.horoscopumcompose.presentation.detail.header.DetailHeader
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

        LaunchedEffect(key1 = true) {
            viewModel.init(signName)
        }

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
                    delay(200)
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

    val paddingValues = WindowInsets.systemBars.asPaddingValues()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
            .padding(top = paddingValues.calculateTopPadding()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color.Transparent
        ) {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back icon",
                    tint = MaterialTheme.colors.onPrimary
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
fun Loading() {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F, targetValue = 360F, animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing, delayMillis = 0), repeatMode = RepeatMode.Restart
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
            .background(Color.Black.copy(alpha = 0.3f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_moon),
            contentDescription = "Loading icon",
            modifier = Modifier.rotate(angle)
        )

        Spacer(modifier = Modifier.height(32.dp))

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

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onRetryButtonClick,
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text(text = stringResource(id = R.string.error_detail_ok_button), color = MaterialTheme.colors.onPrimary)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    Detail(
        sign = HoroscopeSigns.Cancer,
        signHoroscopeData = mapOf(),
        showDetailData = true,
        showDetailHeader = true
    ) {}
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