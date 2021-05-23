package com.example.esiea_3a_mobile.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.esiea_3a_mobile.data.model.CovidStat

 class CovidListViewModel(): ViewModel(){

    private val covidList: MutableLiveData<List<CovidStat>> by lazy {
        MutableLiveData<List<CovidStat>>().also {
            loadStats()
        }
    }

    fun getList(): LiveData<List<CovidStat>> {
        return covidList
    }

    private fun loadStats() {
        // Do an asynchronous operation to fetch users.
    }

}