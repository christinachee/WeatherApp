package com.waichee.weatherapp02.data.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeelsLike(
    @Json(name = "day")
    val day: Double = 0.0,
    @Json(name = "night")
    val night: Double = 0.0,
    @Json(name = "eve")
    val eve: Double = 0.0,
    @Json(name = "morn")
    val morn: Double = 0.0
)