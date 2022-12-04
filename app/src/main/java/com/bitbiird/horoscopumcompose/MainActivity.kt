package com.bitbiird.horoscopumcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
                navController = rememberNavController()
                SetUpNavigation(navController)
                SetUpStatusBar()
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