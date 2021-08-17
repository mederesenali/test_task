package com.example.testtask.di

import com.example.testtask.data.api.PhotosApi
import com.example.testtask.data.api.WeatherApi
import com.example.testtask.utils.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.WEATHER_URL

    @Provides
    fun provideLoggingInterceptor()=HttpLoggingInterceptor().
        apply { level = HttpLoggingInterceptor.Level.BODY }


    @Provides
    //@Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(interceptor).build();

    @Provides
    @Singleton
    fun provideRetrofit(WEATHER_URL: String, client: OkHttpClient): WeatherApi =
        Retrofit.Builder()
            .client(client)
            .baseUrl(WEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)



}