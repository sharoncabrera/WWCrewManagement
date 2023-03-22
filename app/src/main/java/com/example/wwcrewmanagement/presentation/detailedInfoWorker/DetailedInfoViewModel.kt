package com.example.wwcrewmanagement.presentation.detailedInfoWorker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wwcrewmanagement.domain.use_case.GetWorkerByIdUseCase
import com.example.wwcrewmanagement.model.Worker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getWorkerByIdUseCase: GetWorkerByIdUseCase
) : ViewModel() {

    private val workerId: Int? = savedStateHandle.get<Int>(WORKER_ID_SAVED_STATE_KEY)

    private val _workerFlow = MutableStateFlow<Worker?>(null)
    val workerFlow: StateFlow<Worker?> get() = _workerFlow

    init {
        viewModelScope.launch {
            workerId?.let { id ->
                getWorkerByIdUseCase(id).apply {
                    _workerFlow.value = this
                }
            }
        }
    }

    companion object {
        const val WORKER_ID_SAVED_STATE_KEY = "workerId"
    }
}