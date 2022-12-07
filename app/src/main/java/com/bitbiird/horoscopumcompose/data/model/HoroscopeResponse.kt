package com.bitbiird.horoscopumcompose.data.model

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class HoroscopeResponse(
    var compatibility: String = "",
    var description: String = "",
    @SerializedName("current_date")
    var currentDate: String = "",
    @SerializedName("lucky_number")
    var luckyNumber: String = "",
    @SerializedName("lucky_time")
    var luckyTime: String = ""
)