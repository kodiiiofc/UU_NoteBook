package com.kodiiiofc.urbanuniversity.diary.domain.weather.models

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)