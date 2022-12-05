package com.bitbiird.horoscopumcompose.data.repository

import com.bitbiird.horoscopumcompose.data.model.HoroscopeResponse
import com.bitbiird.horoscopumcompose.data.network.HoroscopeService
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(private val service: HoroscopeService) {

    suspend fun getHoroscopeData(sign: String, day: String): HoroscopeResponse? =
        service.getHoroscopeData(sign, day)

}