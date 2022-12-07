package com.bitbiird.horoscopumcompose.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitbiird.horoscopumcompose.data.model.ErrorType
import com.bitbiird.horoscopumcompose.data.model.HoroscopeResponse
import com.bitbiird.horoscopumcompose.data.model.NetworkState
import com.bitbiird.horoscopumcompose.domain.useCase.GetHoroscopeDataUseCase
import com.bitbiird.horoscopumcompose.util.constants.Day
import com.bitbiird.horoscopumcompose.util.helpers.InternetConnectionHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getHoroscopeUseCase: GetHoroscopeDataUseCase,
    private val internetConnectionHelper: InternetConnectionHelper
) : ViewModel() {

    private val _horoscopeDataState = MutableStateFlow<NetworkState<Any>>(NetworkState.Loading)
    val horoscopeDataState = _horoscopeDataState

    fun init(sign: String) {
        viewModelScope.launch {
            _horoscopeDataState.value = NetworkState.Loading

            if (internetConnectionHelper.isConnected()) {
                val result = withContext(Dispatchers.IO) {
                    getHoroscopeData(sign)
                }

                if (result.isNotEmpty() && result.values.all { it != null }) {
                    _horoscopeDataState.value = NetworkState.Success<Map<Day, HoroscopeResponse>>(result)
                } else {
                    _horoscopeDataState.value = NetworkState.Error(ErrorType.NO_INTERNET_ERROR)
                }
            } else {
                _horoscopeDataState.value = NetworkState.Error(ErrorType.API_ERROR)
            }

        }
    }

    private suspend fun getHoroscopeData(sign: String): Map<Day, HoroscopeResponse?> {
        val deferredHoroscopeList = mutableListOf<Deferred<HoroscopeResponse?>>()
        coroutineScope {
            for (day in Day.values()) {
                val horoscopeData = async {
                    getHoroscopeUseCase(sign, day.dayString.lowercase())
                }
                deferredHoroscopeList.add(horoscopeData)
            }
        }
        val horoscopeDataList = deferredHoroscopeList.awaitAll()
        return Day.values().zip(horoscopeDataList).toMap()
    }

}

