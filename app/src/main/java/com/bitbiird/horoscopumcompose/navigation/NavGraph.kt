package com.bitbiird.horoscopumcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bitbiird.horoscopumcompose.presentation.detail.DetailScreen
import com.bitbiird.horoscopumcompose.presentation.home.HomeScreen
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
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.route, arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val signId = backStackEntry.arguments?.getInt(DETAIL_ARGUMENT_KEY) ?: 0
            DetailScreen(navController, signId)
        }
    }
}