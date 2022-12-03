package com.bitbiird.horoscopumcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bitbiird.horoscopumcompose.navigation.SetUpNavigation
import com.bitbiird.horoscopumcompose.ui.theme.HoroscopumComposeTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HoroscopumComposeTheme {
                navController = rememberNavController()
                SetUpNavigation(navController)
            }
        }
    }
}