package com.example.testtask.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Photo(
    @SerializedName("id")
    @Expose
    val id:String,
    @SerializedName("author")
    @Expose
    val author:String,
    @SerializedName("width")
    @Expose
    val width:Int,
    @SerializedName("height")
    @Expose
    val height:Int,
    @SerializedName("url")
    @Expose
    val url:String,
    @SerializedName("download_url")
    @Expose
    val downloadUrl:String,
    ):Parcelable



