package com.ilgiz.testapp.weathertestapp.data.repository

import com.ilgiz.testapp.weathertestapp.data.api.ApiService
import com.ilgiz.testapp.weathertestapp.data.model.City
import com.ilgiz.testapp.weathertestapp.data.model.WeatherResponseBody
import javax.inject.Inject

class Repository @Inject internal constructor(var apiService: ApiService) {
    suspend fun getWeatherInfo(city: City?, count: Int?): WeatherResponseBody {
        return apiService.getWeather(lat = city?.lat, lon = city?.lon, cnt = count)
    }
}