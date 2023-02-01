package com.example.wwcrewmanagement.network

import com.example.wwcrewmanagement.model.ApiResponse
import com.example.wwcrewmanagement.model.Worker
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


class ApiClient {
    companion object {
        lateinit var instance: ApiClient
    }

    var page = 1
    var numberTotalofPages = 1

    private val baseUrl = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/"
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }


    suspend fun getData(): Result<List<Worker>> {

        if (page > numberTotalofPages) {
            return Result.success(listOf())
        }

        val response = client.get {
            url("${baseUrl}oompa-loompas")
            parameter("page", page)
        }
        return if (response.status.isSuccess()) {
            page++
            val result: ApiResponse = response.body()
            numberTotalofPages = result.total
            Result.success(result.results)
        } else {
            Result.failure(error("Something went wrong"))
        }
    }
}
