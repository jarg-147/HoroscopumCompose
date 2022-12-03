package com.bitbiird.horoscopumcompose.util.enums

import com.bitbiird.horoscopumcompose.R

enum class HoroscopeSigns(val id: Int, val signName: Int, val signIcon: Int, val signDate: Int) {
    Capricorn(1, R.string.capricorn, R.drawable.ic_capricorn, R.string.capricorn_date),
    Aquarium(2, R.string.aquarium, R.drawable.ic_aquarium, R.string.aquarium_date),
    Pisces(3, R.string.pisces, R.drawable.ic_pisces, R.string.pisces_date),
    Aries(4, R.string.aries, R.drawable.ic_aries, R.string.aries_date),
    Taurus(5, R.string.taurus, R.drawable.ic_taurus, R.string.taurus_date),
    Gemini(6, R.string.gemini, R.drawable.ic_gemini, R.string.gemini_date),
    Cancer(7, R.string.cancer, R.drawable.ic_cancer, R.string.cancer_date),
    Leo(8, R.string.leo, R.drawable.ic_leo, R.string.leo_date),
    Virgo(9, R.string.virgo, R.drawable.ic_virgo, R.string.virgo_date),
    Libra(10, R.string.libra, R.drawable.ic_libra, R.string.libra_date),
    Scorpio(11, R.string.scorpio, R.drawable.ic_scorpio, R.string.scorpio_date),
    Sagittarius(12, R.string.sagittarius, R.drawable.ic_sagittarius, R.string.sagittarius_date);
}
