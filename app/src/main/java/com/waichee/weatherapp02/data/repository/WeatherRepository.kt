package com.waichee.weatherapp02.data.repository

import androidx.lifecycle.LiveData
import com.waichee.weatherapp02.data.entities.WeatherResponse
import com.waichee.weatherapp02.data.remote.WeatherRemoteDataSource
import com.waichee.weatherapp02.utils.Resource
import com.waichee.weatherapp02.utils.performGetOperation
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource
) {
    fun getWeather(lat: String, lon: String): LiveData<Resource<WeatherResponse>> {
        return performGetOperation(
            networkCall = {remoteDataSource.getWeather(lat, lon)}
        )
    }
}