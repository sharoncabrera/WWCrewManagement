package com.example.wwcrewmanagement.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Favorite(
    var color: String,
    var food: String,
    @SerialName("random_string")
    var randomString: String,
    var song: String,
):java.io.Serializable
