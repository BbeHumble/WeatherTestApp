package com.ilgiz.testapp.weathertestapp.data.api

import com.ilgiz.testapp.weathertestapp.data.model.WeatherResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("forecast/daily")
    suspend fun getWeather(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("cnt") cnt: Int?,
        @Query("appid") appId: String? = "33aa634c216259f797f35e862f073b40",
        @Query("units") units :String? = "metric",
        @Query("lang") lang:String? = "cz"
    ): WeatherResponseBody

}