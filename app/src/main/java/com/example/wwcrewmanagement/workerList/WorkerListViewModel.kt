package com.example.wwcrewmanagement.workerList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wwcrewmanagement.model.Filter
import com.example.wwcrewmanagement.model.Worker
import com.example.wwcrewmanagement.network.ApiClient
import kotlinx.coroutines.launch

class WorkerViewModel : ViewModel() {

    private val _workers = MutableLiveData<List<Worker>>(listOf())
    val workers: LiveData<List<Worker>> get() = _workers
    private val _professions = MutableLiveData<List<String>>(listOf())
    val professions: LiveData<List<String>> get() = _professions
    var professionsNameList: List<String> = listOf()
    var isLoading = MutableLiveData<Boolean>().apply { value = false }

    var filteredWorkers = MutableLiveData<List<Worker>>()
    var filter = MutableLiveData<Filter>().apply { value = Filter.NONE }


    private var _currentGender = MutableLiveData<String>()
    val currentGender2: LiveData<String>
        get() = _currentGender

    private var _currentProfession = MutableLiveData<String>()
    val currentProfession: LiveData<String>
        get() = _currentProfession

    var currentFilterOption = Filter.NONE

    fun setCurrentGender(gender: String) {
        _currentGender.value = gender
    }

    fun setCurrentProfession(profession: String) {
        _currentProfession.value = profession
    }

    fun fetchData() {
        isLoading.value = true
        viewModelScope.launch {
            val currentWorkers = _workers.value
            val newData = ApiClient.instance.getData().getOrDefault(listOf())

            _workers.value = currentWorkers?.plus(newData)

            filterWorkersBy()

            isLoading.value = false
        }
    }

    private fun filtrationOperation() {
        if (isFilteringByGender() || isFilteringByProfession()) {
            if (isFilteringByProfession()) {
                if (isFilteringByGender()) {
                    currentFilterOption = Filter.BOTH
                } else {
                    currentFilterOption = Filter.PROFESSION
                }
            } else {
                currentFilterOption = Filter.GENDER
            }
        } else {
            currentFilterOption = Filter.NONE
        }
    }

    fun isFilteringByProfession(): Boolean {
        return _currentProfession.value?.isNotBlank() == true
    }

    fun isFilteringByGender(): Boolean {
        return _currentGender.value?.isNotBlank() == true
    }

    fun filterWorkersBy() {
        filtrationOperation()
        filteredWorkers.value = when (currentFilterOption) {
            Filter.GENDER -> workers.value?.filter { it.gender == _currentGender.value }
            Filter.PROFESSION -> workers.value?.filter { it.profession == _currentProfession.value }
            Filter.BOTH -> workers.value?.filter { it.profession == _currentProfession.value && it.gender == _currentGender.value }
            else -> workers.value
        }
    }

    fun extractProfessions(workers: List<Worker>) {
        _professions.value = workers.map { it.profession }.distinct()
    }

    init {
        fetchData()
    }

    fun loadNextEvents() {
        fetchData()
    }
}