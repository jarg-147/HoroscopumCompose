package com.bitbiird.horoscopumcompose.data.network

import com.bitbiird.horoscopumcompose.data.model.HoroscopeResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface HoroscopeApiClient {
    @POST("/")
    suspend fun getHoroscope(
        @Query("sign") sign: String,
        @Query("day") day: String
    ): Response<HoroscopeResponse?>
}