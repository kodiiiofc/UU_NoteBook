package com.kodiiiofc.urbanuniversity.diary.domain.weather

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.kodiiiofc.urbanuniversity.diary.domain.weather.models.CurrentWeather

interface ApiInterface {

    @GET("weather?")
    suspend fun getCurrentWeatherByCity(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ) : Response<CurrentWeather>

    @GET("weather?")
    suspend fun getCurrentWeatherByLocation(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ) : Response<CurrentWeather>
}