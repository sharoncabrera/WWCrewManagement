package com.example.wwcrewmanagement.domain.use_case

import com.example.wwcrewmanagement.domain.repository.WorkerRepository
import javax.inject.Inject

class GetWorkersUseCase @Inject constructor(
    private val workerRepository: WorkerRepository
) {
    suspend operator fun invoke() = workerRepository.getWorkers()
}