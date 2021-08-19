package com.example.testtask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.testtask.data.api.PhotosApi
import com.example.testtask.data.model.Photo
import com.example.testtask.data.model.ResponseApi

import com.example.testtask.data.paging.PhotoPagingSource
import com.example.testtask.di.PhotoAPI
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(@PhotoAPI private val photosApi: PhotosApi) : ViewModel() {
    companion object {
        private const val TAG = "PhotoViewModel"
    }

    val listData = Pager(
        PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            initialLoadSize = 10
        )
    ) {
        PhotoPagingSource(photosApi)
    }.flow

    var data = MutableLiveData<List<Photo>>()

    fun getPhoto() {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val response = photosApi.getPhotos(1, 10)
                if (response.isSuccessful) {
                    data.value = response.body()
                } else {
                    Log.d("mederbek", "no photos ")
                }
            }
        }
        /* photosApi.getPhotos(1, 10).enqueue(object : Callback<List<Photo>> {
             override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                 data.value = response.body()
                 Log.d(TAG, "onResponse: ${response.body()}")
             }

             override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                 Log.e(TAG, "onFailure: cannot download", t)
             }

         })*/
    }
}