package com.example.wwcrewmanagement.model

data class Worker(
    val id: Int,
    var name: String = "",
    var image: String = "",
    var profession: String = "",
    var gender: String = "",
    var surname: String = "",
    var email: String = "",
    val age: Int = 0,
    var country: String = "",
    var height: Int = 0,
)
