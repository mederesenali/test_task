package com.example.testtask.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.testtask.data.api.PhotosApi
import com.example.testtask.data.api.WeatherApi
import com.example.testtask.data.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val  weatherApi: WeatherApi)
:ViewModel(){
    val listData=MutableLiveData<WeatherResponse>()


    val weatherResponse: LiveData<WeatherResponse>
        get() = listData


    init {
        getWeather()
    }

    private fun getWeather() = viewModelScope.launch {
        weatherApi.getWeatherData(498817,"fc8581245a9d7e0ef2f531b01a688f9e").let { response ->

            if (response.isSuccessful) {
                listData.postValue(response.body())
            } else {
                Log.d("tag", "getWeather Error: ${response.code()}")
            }
        }
    }
}

