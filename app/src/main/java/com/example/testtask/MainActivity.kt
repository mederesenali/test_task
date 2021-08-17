package com.example.testtask

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.testtask.databinding.ActivityMainBinding

import com.example.testtask.ui.adapter.PhotoAdapter

import com.example.testtask.viewmodel.WeatherViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var photoPhotoAdapter: PhotoAdapter
 //   private val viewModel: PhotoViewModel by viewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()

    var weather:String ? =null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_photos, R.id.navigation_login))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



     weatherViewModel.weatherResponse.observe(this,{w->
         weather= w.weather.toString()
     })

        val button = findViewById<Button>(R.id.enter)
        button.setOnClickListener {



            Log.d("BUTTON", "Button clicked!!!!")
            val snack = Snackbar.make(it,"The weather in SankPeterBurg is ${weather} : ",Snackbar.LENGTH_LONG)
            snack.show()
        }



    }



//    private fun loadData() {
//        photoPhotoAdapter = PhotoAdapter()
//        lifecycleScope.launch {
//            viewModel.listData.collectLatest {
//                Log.d("MainActivity", "load: $it")
//                photoPhotoAdapter.submitData(it)
//            }
//        }
//    }


}
