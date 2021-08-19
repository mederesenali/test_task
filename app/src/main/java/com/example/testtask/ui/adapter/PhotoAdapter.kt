package com.example.testtask.ui.adapter

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.testtask.R
import com.example.testtask.data.model.Photo
import com.example.testtask.databinding.PhotoLayoutBinding

class PhotoAdapter : PagingDataAdapter<Photo, PhotoAdapter.ImageViewHolder>(diffCallback) {

    inner class ImageViewHolder(val binding: PhotoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return newItem.id == oldItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            PhotoLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currChar = getItem(position)

        holder.binding.apply {

            holder.itemView.apply {
                tvAuthor.text = "${currChar?.author}"

                val imageLink = currChar?.download_url
                Glide.with(imageView)
                    .load(imageLink)
                    .centerCrop()
                    .placeholder(R.drawable.logo)
                    .into(imageView)
            }
        }

    }


}