package com.bitbiird.horoscopumcompose.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object List : Screen("list_screen")
    object Detail : Screen("detail_screen/{horoscopeSignId}") {
        fun setHoroscopeSignId(horoscopeSignId: Int): String  {
            return "detail_screen/$horoscopeSignId"
        }
    }
}