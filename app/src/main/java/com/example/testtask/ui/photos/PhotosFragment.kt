package com.example.testtask.ui.photos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import com.example.testtask.databinding.PhotoFragmentBinding
import com.example.testtask.databinding.PhotoLayoutBinding
import com.example.testtask.ui.adapter.PhotoAdapter
import com.example.testtask.viewmodel.PhotoViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosFragment : Fragment() {
    private var _binding: PhotoFragmentBinding? = null
    private val binding: PhotoFragmentBinding
        get() = _binding!!

    private val viewModel: PhotoViewModel by viewModels()
    private val photoPhotoAdapter: PhotoAdapter = PhotoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PhotoFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        initView()
    }

    private fun initView() = with(binding) {
        recyclerView.apply {
            adapter = photoPhotoAdapter
        }

        photoPhotoAdapter.addLoadStateListener { state: CombinedLoadStates ->
            Log.e(PhotosFragment::class.simpleName, "adapter state: $state")
        }

        viewModel.data.observe(viewLifecycleOwner) {
            photoPhotoAdapter.submitData(viewLifecycleOwner.lifecycle, PagingData.from(it))
        }
    }

    private fun loadData() {
        viewModel.getPhoto()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listData.collectLatest { pagingData ->
                Log.d(
                    "MainActivity", "loaded data: ${
                        Gson().toJson(pagingData)
                    }"
                )
                photoPhotoAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}