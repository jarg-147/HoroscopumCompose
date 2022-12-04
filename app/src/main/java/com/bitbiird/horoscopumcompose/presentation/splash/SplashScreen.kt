package com.bitbiird.horoscopumcompose.presentation.splash

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bitbiird.horoscopumcompose.R
import com.bitbiird.horoscopumcompose.navigation.Screen
import com.bitbiird.horoscopumcompose.ui.theme.HoroscopumTypography
import com.bitbiird.horoscopumcompose.ui.theme.splashBackgroundColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController
) {

    var showSplashScreen by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        delay(500)
        showSplashScreen = true
        delay(1000)
        showSplashScreen = false
        delay(500)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    Splash(showSplashScreen)

}

@Composable
fun Splash(showSplashScreen: Boolean) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.splashBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = showSplashScreen,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally{ (it / 2) }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_constellations),
                contentDescription = "Splash logo",
                modifier = Modifier.size(86.dp)
            )
        }
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth().padding(top = 48.dp), visible = showSplashScreen,
            enter = fadeIn() + slideInHorizontally { (it / 2) },
            exit = fadeOut() + slideOutHorizontally()
        ) {
            Text(
                stringResource(id = R.string.app_name),
                style = HoroscopumTypography.h1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(showSplashScreen = true)
}