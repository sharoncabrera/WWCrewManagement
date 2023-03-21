package com.example.wwcrewmanagement.presentation.detailedInfoWorker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wwcrewmanagement.model.Worker
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailedInfoViewModel @Inject constructor(
) : ViewModel() {

    private var _worker = MutableLiveData<Worker>()
    val worker: LiveData<Worker>
        get() = _worker

    private var _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = name

    fun setWorker(worker: Worker) {
        _worker.value = worker
        _name.value = worker.name
    }
}