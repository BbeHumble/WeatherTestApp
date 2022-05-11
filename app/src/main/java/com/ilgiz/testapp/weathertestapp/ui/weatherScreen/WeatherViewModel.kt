package com.ilgiz.testapp.weathertestapp.ui.weatherScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilgiz.testapp.weathertestapp.data.model.City
import com.ilgiz.testapp.weathertestapp.data.model.WeatherItem
import com.ilgiz.testapp.weathertestapp.data.repository.Repository
import com.ilgiz.testapp.weathertestapp.di.CoroutineDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: Repository,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val weathersItem = MutableLiveData<List<WeatherItem>>()

    var selectedCity: City? = null

    fun getWeather(city: City?, count: Int?) {
        viewModelScope.launch(coroutineDispatcherProvider.IO()) {
            try {
                val weatherInfo = repository.getWeatherInfo(city, count)
                weathersItem.postValue(weatherInfo.list.map {
                    WeatherItem(parseTimestampToDate(it.dt), it.temp.day.split(".")[0])
                })
            } catch (exception: Exception) {
                errorMessage.postValue(exception.toString())
            }

        }
    }

    private fun parseTimestampToDate(timestamp: String): Date {
        try {
            return Date(timestamp.toLong() * 1000)
        } catch (e: Exception) {
            throw e
        }
    }

}