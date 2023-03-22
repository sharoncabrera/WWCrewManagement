package com.example.wwcrewmanagement.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkerEntity(
    @PrimaryKey val id: Int,
    var name: String,
    var surname: String,
    var gender: String,
    var image: String,
    var profession: String,
    var email: String,
    val age: Int,
    var country: String,
    var height: Int,
)