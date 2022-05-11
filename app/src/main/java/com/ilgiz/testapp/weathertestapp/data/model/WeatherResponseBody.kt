package com.ilgiz.testapp.weathertestapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherResponseBody(
    @SerializedName("list")
    val list: List<Weather>
) : Parcelable

@Parcelize
data class Weather(
    @SerializedName("dt")
    val dt:String,
    @SerializedName("temp")
    val temp: Temp
) : Parcelable

@Parcelize
data class Temp(
    @SerializedName("day")
    val day: String
) : Parcelable