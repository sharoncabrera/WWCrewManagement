package com.example.wwcrewmanagement.detailedInfoWorker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wwcrewmanagement.model.Worker

class DetailedInfoViewModel: ViewModel() {

    private var _worker = MutableLiveData<Worker>()
    val worker: LiveData<Worker>
        get() = _worker

    fun setWorker(worker: Worker) {
        _worker.value = worker
    }
}