package com.example.testtask.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testtask.databinding.LoginFragmentBinding
import com.example.testtask.viewmodel.WeatherViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: LoginFragmentBinding? = null
    private val binding: LoginFragmentBinding
        get() = _binding!!

    private val weatherViewModel: WeatherViewModel by viewModels()
    private var weather: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherViewModel.weatherResponse.observe(viewLifecycleOwner, { w ->
            weather = w.main.temp.toString()
        })

        var email=binding.editTextTextPersonName.text
        val pasword=binding.editTextTextPassword.text


        binding.enter.setOnClickListener {
            Log.d("BUTTON", "Button clicked!!!!")
            val snack = Snackbar.make(
                it,
                "The weather in SankPeterBurg is : $weather C ",
                Snackbar.LENGTH_LONG
            )
            snack.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}
