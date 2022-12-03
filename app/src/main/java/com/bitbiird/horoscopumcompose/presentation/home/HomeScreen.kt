package com.bitbiird.horoscopumcompose.presentation.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bitbiird.horoscopumcompose.R
import com.bitbiird.horoscopumcompose.navigation.Screen
import com.bitbiird.horoscopumcompose.ui.theme.HoroscopumTypography
import com.bitbiird.horoscopumcompose.ui.theme.homeButtonColor

@Composable
fun HomeScreen(navController: NavController) {

    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 360F, targetValue = 0F, animationSpec = infiniteRepeatable(
            animation = tween(60000, easing = LinearEasing)
        )
    )

    Home(rotateAnim = angle,
        onReadButtonClick = {
            navController.navigate(Screen.List.route)
        }, onCompatibilityButtonClick = {
            navController.navigate(Screen.CompatibilityList.route)
        })

}

@Composable
fun Home(
    rotateAnim: Float,
    onReadButtonClick: () -> Unit,
    onCompatibilityButtonClick: () -> Unit,
) {
    Box(
        Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.stars_background), contentDescription = "background")
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                stringResource(id = R.string.app_name),
                style = HoroscopumTypography.h1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 82.dp)
            )
            Spacer(modifier = Modifier.height(64.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_constellations),
                contentDescription = "home image",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(148.dp)
                    .rotate(rotateAnim)
            )
            Spacer(modifier = Modifier.height(84.dp))
            Button(
                onClick = onReadButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.homeButtonColor)
            ) {
                Text(text = stringResource(id = R.string.button_read_my_horoscope_text), style = HoroscopumTypography.button)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onCompatibilityButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.homeButtonColor)
            ) {
                Text(text = stringResource(id = R.string.button_sign_compatibility_text), style = HoroscopumTypography.button)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(0f, {}, {})
}