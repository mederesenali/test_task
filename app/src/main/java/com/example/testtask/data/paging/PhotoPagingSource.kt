package com.example.testtask.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testtask.data.api.PhotosApi
import com.example.testtask.data.model.Photo
import java.lang.Exception


class PhotoPagingSource(private val photosApi: PhotosApi) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val currentPage = params.key ?: 1
            val response = photosApi.getPhotos(currentPage, 10)
            val responseData = mutableListOf<Photo>()
            val data = response.body() ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}