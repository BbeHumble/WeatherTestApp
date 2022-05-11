package com.ilgiz.testapp.weathertestapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val cityName:String,
    val lat:String,
    val lon:String,
) : Parcelable