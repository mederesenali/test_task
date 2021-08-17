package com.example.testtask.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testtask.databinding.PhotoFragmentBinding
import com.example.testtask.databinding.PhotoLayoutBinding

class PhotosFragment:Fragment() {
    private var _binding:PhotoFragmentBinding?=null
    private val binding:PhotoFragmentBinding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= PhotoFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}