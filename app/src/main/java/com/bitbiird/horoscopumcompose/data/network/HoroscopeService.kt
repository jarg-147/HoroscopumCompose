package com.bitbiird.horoscopumcompose.data.network

import com.bitbiird.horoscopumcompose.data.model.HoroscopeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HoroscopeService @Inject constructor(private val api: HoroscopeApiClient) {

    suspend fun getHoroscopeData(sign: String, day: String): HoroscopeResponse? {
        return withContext(Dispatchers.IO) {
            api.getHoroscope(sign, day).body()
        }
    }
}