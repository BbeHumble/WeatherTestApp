package com.ilgiz.testapp.weathertestapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class WeatherItem(
    val date: Date,
    val temperature: String
) : Parcelable