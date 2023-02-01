package com.example.wwcrewmanagement.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(var current: Int, var total: Int, var results: List<Worker>)
