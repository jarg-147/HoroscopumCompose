package com.bitbiird.horoscopumcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bitbiird.horoscopumcompose.navigation.SetUpNavigation
import com.bitbiird.horoscopumcompose.ui.theme.HoroscopumComposeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            HoroscopumComposeTheme {
                Box(
                    Modifier.fillMaxSize()
                ) {
                    Image(painter = painterResource(id = R.drawable.stars_background), contentDescription = "background")
                    navController = rememberNavController()
                    SetUpNavigation(navController)
                    SetUpStatusBar()
                }
            }
        }
    }

    @Composable
    private fun SetUpStatusBar() {
        val systemUiController = rememberSystemUiController()
        systemUiController.setSystemBarsColor(
            color = Color.Transparent
        )
    }

}