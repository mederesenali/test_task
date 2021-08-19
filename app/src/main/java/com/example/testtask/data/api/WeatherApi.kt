package com.example.testtask.data.api

import com.example.testtask.data.model.ResponseApi
import com.example.testtask.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("data/2.5/weather")
    suspend  fun getWeatherData(
        @Query("id") id: Int,
        @Query("appid") appid: String,
        @Query("lang") lang: String,
        @Query("units") units: String,
    ): Response<WeatherResponse>
}