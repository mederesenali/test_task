package com.example.testtask.di

import com.example.testtask.data.api.PhotosApi
import com.example.testtask.data.api.WeatherApi
import com.example.testtask.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @PicturesBaseUrl
    @Provides
    @Singleton
    fun providePicturesBaseUrl() = Constants.PICTURES_BASE_URL

    @OpenWeatherBaseUrl
    @Provides
    @Singleton
    fun provideOpenWeatherBaseUrl() = Constants.OPEN_WEATHER_BASE_URL

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(Constants.OkHttpClient.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.OkHttpClient.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.OkHttpClient.READ_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @WeatherAPI
    @Provides
    @Singleton
    fun provideWeatherRetrofit(
        @OpenWeatherBaseUrl OPEN_WEATHER_BASE_URL: String,
        client: OkHttpClient
    ): WeatherApi =
        Retrofit.Builder()
            .client(client)
            .baseUrl(OPEN_WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(WeatherApi::class.java)

    @PhotoAPI
    @Provides
    @Singleton
    fun providePicturesRetrofit(
        @PicturesBaseUrl PICTURES_BASE_URL: String,
        client: OkHttpClient
    ): PhotosApi =
        Retrofit.Builder()
            .client(client)
            .baseUrl(PICTURES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
         //   .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(PhotosApi::class.java)


}