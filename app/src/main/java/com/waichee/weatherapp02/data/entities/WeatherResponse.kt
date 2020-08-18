package com.waichee.weatherapp02.data.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "lat")
    val lat: Double = 0.0,
    @Json(name = "lon")
    val lon: Double = 0.0,
    @Json(name = "timezone")
    val timezone: String = "",
    @Json(name = "timezone_offset")
    val timezoneOffset: Int = 0,
    @Json(name = "current")
    val current: Current = Current(),
    @Json(name = "hourly")
    val hourly: List<Hourly> = listOf(),
    @Json(name = "daily")
    val daily: List<Daily> = listOf()
)