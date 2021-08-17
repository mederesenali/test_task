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
import com.example.testtask.data.paging.PhotPaginSourse
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


//@HiltViewModel
//class PhotoViewModel //@Inject constructor(private val photosApi: PhotosApi)
//    : ViewModel() {
////    val listData = Pager(PagingConfig(pageSize = 1)) {
////      //  PhotPaginSourse(photosApi)
////    }.flow.cachedIn(viewModelScope)
//}