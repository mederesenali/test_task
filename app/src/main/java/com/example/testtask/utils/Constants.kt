package com.example.testtask.utils

object Constants {
    const val OPEN_WEATHER_BASE_URL: String = "https://api.openweathermap.org/"
    const val PICTURES_BASE_URL: String = "https://picsum.photos/"

    object OkHttpClient {
        const val CONNECT_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
    }
}