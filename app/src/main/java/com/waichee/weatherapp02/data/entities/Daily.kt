package com.waichee.weatherapp02.data.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Daily(
    @Json(name = "dt")
    val dt: Int = 0,
    @Json(name = "sunrise")
    val sunrise: Int = 0,
    @Json(name = "sunset")
    val sunset: Int = 0,
    @Json(name = "temp")
    val temp: Temp = Temp(),
    @Json(name = "feels_like")
    val feelsLike: FeelsLike = FeelsLike(),
    @Json(name = "pressure")
    val pressure: Int = 0,
    @Json(name = "humidity")
    val humidity: Int = 0,
    @Json(name = "dew_point")
    val dewPoint: Double = 0.0,
    @Json(name = "wind_speed")
    val windSpeed: Double = 0.0,
    @Json(name = "wind_deg")
    val windDeg: Int = 0,
    @Json(name = "weather")
    val weather: List<Weather> = listOf(),
    @Json(name = "clouds")
    val clouds: Int = 0,
    @Json(name = "pop")
    val pop: Double = 0.0,
    @Json(name = "uvi")
    val uvi: Double = 0.0
)