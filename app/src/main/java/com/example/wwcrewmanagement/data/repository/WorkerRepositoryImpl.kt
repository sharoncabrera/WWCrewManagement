package com.example.wwcrewmanagement.data.repository

import com.example.wwcrewmanagement.data.mapper.toWorker
import com.example.wwcrewmanagement.domain.repository.WorkerRepository
import com.example.wwcrewmanagement.model.Worker
import com.example.wwcrewmanagement.data.network.ApiClient
import javax.inject.Inject

class WorkerRepositoryImpl @Inject constructor(
    private val api: ApiClient
) : WorkerRepository {
    override suspend fun getWorkers(): List<Worker> {
        return api.getData().getOrDefault(listOf()).map { workerResponse ->
            workerResponse.toWorker()
        }
    }
}