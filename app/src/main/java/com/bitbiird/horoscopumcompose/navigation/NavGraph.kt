package com.bitbiird.horoscopumcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bitbiird.horoscopumcompose.presentation.splash.SplashScreen
import com.bitbiird.horoscopumcompose.util.constants.NavigationConstants.DETAIL_ARGUMENT_KEY

@Composable
fun SetUpNavigation(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            //TODO
        }
        composable(route = Screen.List.route) {
            //TODO
        }
        composable(
            route = Screen.Detail.route, arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {

        }
    }
}