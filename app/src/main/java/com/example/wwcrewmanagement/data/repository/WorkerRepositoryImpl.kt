package com.example.wwcrewmanagement.data.repository

import com.example.wwcrewmanagement.data.local.database.WorkerDao
import com.example.wwcrewmanagement.data.local.mapper.toWorker
import com.example.wwcrewmanagement.data.mapper.toDomain
import com.example.wwcrewmanagement.data.mapper.toWorker
import com.example.wwcrewmanagement.data.network.ApiClient
import com.example.wwcrewmanagement.domain.repository.WorkerRepository
import com.example.wwcrewmanagement.model.Worker
import javax.inject.Inject

class WorkerRepositoryImpl @Inject constructor(
    private val workerDao: WorkerDao,
    private val api: ApiClient
) : WorkerRepository {
    override suspend fun getWorkers(): List<Worker> {
        return api.getData().getOrDefault(listOf()).map { workerResponse ->
            insertWorker(workerResponse.toWorker())
            workerResponse.toWorker()
        }
    }

    override suspend fun getWorkerById(workerId: Int): Worker {
        return workerDao.getWorker(workerId).toWorker()
    }

    override suspend fun insertWorker(worker: Worker) {
        workerDao.insertWorker(worker.toDomain())
    }
}