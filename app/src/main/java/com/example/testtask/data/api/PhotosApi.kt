package com.example.testtask.data.api

import com.example.testtask.data.model.Photo
import com.example.testtask.data.model.ResponseApi
import com.example.testtask.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PhotosApi {

    @GET("v2/list")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<ResponseApi>
}