package com.bitbiird.horoscopumcompose.data.model

import com.bitbiird.horoscopumcompose.util.constants.Day

sealed class NetworkState<out T : Any> {
    data class Success<out T : Any>(val data: Map<Day, HoroscopeResponse?>) : NetworkState<T>()
    data class Error(val error: ErrorType) : NetworkState<Nothing>()
    object Loading : NetworkState<Nothing>()
}

