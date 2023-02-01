package com.example.wwcrewmanagement

import android.app.Application
import io.ktor.client.*
import io.ktor.client.engine.cio.*

class App : Application() {

    fun main() {
        val client = HttpClient(CIO)
    }
}
