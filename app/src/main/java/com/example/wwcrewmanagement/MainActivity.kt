package com.example.wwcrewmanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = resources.getColor(R.color.purple_500, theme)
        window.navigationBarColor = resources.getColor(R.color.purple_200, theme)
        setContentView(R.layout.activity_main)
    }
}


