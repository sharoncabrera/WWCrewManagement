package com.example.wwcrewmanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wwcrewmanagement.network.ApiClient


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiClient.instance = ApiClient()
        setContentView(R.layout.activity_main)
    }
}


