package com.waichee.weatherapp02.data.remote

import com.waichee.weatherapp02.API_KEY
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private val weatherService: WeatherService
): BaseDataSource() {

    suspend fun getWeather(
        lat: String, lon: String
    ) = getResult { weatherService.getWeather(lat = lat, lon = lon, part = "minutely",
        appid = API_KEY, units = "metric") }
}