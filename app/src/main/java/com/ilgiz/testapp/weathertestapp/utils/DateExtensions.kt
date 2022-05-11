package com.ilgiz.testapp.weathertestapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.getDayOfWeek(): String {
    return SimpleDateFormat("EE", Locale.getDefault()).format(this)
}

fun Date.getDateOfDay(): String {
    return SimpleDateFormat("MM.dd", Locale.getDefault()).format(this)

}