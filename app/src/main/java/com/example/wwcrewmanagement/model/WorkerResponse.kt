package com.example.wwcrewmanagement.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkerResponse(
    val id: Int,
    @SerialName("first_name")
    var name: String,
    @SerialName("last_name")
    var surname: String,
    var gender: String,
    var image: String,
    var profession: String,
    var email: String,
    val age: Int,
    var country: String,
    var height: Int,
    var favorite: Favorite
) : java.io.Serializable
