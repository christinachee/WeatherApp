package com.waichee.weatherapp02.data.remote

import com.waichee.weatherapp02.data.entities.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&appid={YOUR API KEY}
interface WeatherService {
    @GET("onecall")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") part: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): Response<WeatherResponse>
}