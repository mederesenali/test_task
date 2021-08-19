package com.example.testtask.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OpenWeatherBaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PicturesBaseUrl

// RETROFIT
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WeatherAPI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PhotoAPI