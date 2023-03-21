package com.example.wwcrewmanagement.data.mapper

import com.example.wwcrewmanagement.model.Worker
import com.example.wwcrewmanagement.model.WorkerResponse

fun WorkerResponse.toWorker(): Worker {
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