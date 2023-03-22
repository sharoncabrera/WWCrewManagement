package com.example.wwcrewmanagement.data.local.mapper

import com.example.wwcrewmanagement.data.local.database.entities.WorkerEntity
import com.example.wwcrewmanagement.model.Worker


fun WorkerEntity.toWorker(): Worker {
    return Worker(
        id = id,
        name = name,
        image = image,
        profession = profession,
        gender = gender,
        surname = surname,
        email = email,
        age = age,
        country = country,
        height = height,
        //favorite = favorite
    )
}