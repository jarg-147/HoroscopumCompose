package com.bitbiird.horoscopumcompose.domain.useCase

import com.bitbiird.horoscopumcompose.data.repository.HoroscopeRepository
import javax.inject.Inject

class GetHoroscopeDataUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {

    suspend operator fun invoke(
        sign: String,
        day: String
    ) = repository.getHoroscopeData(sign, day)

}