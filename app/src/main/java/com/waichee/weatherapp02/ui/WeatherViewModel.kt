package com.waichee.weatherapp02.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.waichee.weatherapp02.data.repository.WeatherRepository

class WeatherViewModel @ViewModelInject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    val weather = repository.getWeather("33.441792", "-94.037689")
}