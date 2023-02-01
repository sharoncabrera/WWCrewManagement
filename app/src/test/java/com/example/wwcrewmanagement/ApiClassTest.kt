package com.example.wwcrewmanagement

import com.example.wwcrewmanagement.network.ApiClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class ApiClassTest {

    @Test
    fun `Test page and numberTotalofPages increment correctly`() {
        runBlocking {
            val apiClient = ApiClient()
            apiClient.page = 1
            apiClient.numberTotalofPages = 1
            apiClient.getData()
            assertTrue(apiClient.page == 2)
            assertTrue(apiClient.numberTotalofPages > 1)
        }
    }
}