package com.example.birdcounter.ui


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.birdcounter.R
import com.example.birdcounter.databinding.ActivityMainBinding
import com.example.birdcounter.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var mainActivityBinding: ActivityMainBinding


    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
            executePendingBindings()
        }
    }




}

