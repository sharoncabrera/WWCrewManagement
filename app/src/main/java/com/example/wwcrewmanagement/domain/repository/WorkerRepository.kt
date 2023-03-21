package com.example.wwcrewmanagement.domain.repository

import com.example.wwcrewmanagement.model.Worker

interface WorkerRepository {
    suspend fun getWorkers(): List<Worker>
}