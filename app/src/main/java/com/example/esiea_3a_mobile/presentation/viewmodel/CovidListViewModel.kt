package com.example.esiea_3a_mobile.presentation.viewmodel

import androidx.lifecycle.*
import com.example.esiea_3a_mobile.data.api.ApiInstance
import com.example.esiea_3a_mobile.data.api.CovidAPI
import com.example.esiea_3a_mobile.data.model.CovidStat
import com.example.esiea_3a_mobile.presentation.view.list.CovidListModel
import kotlinx.coroutines.launch

class CovidListViewModel(): ViewModel(){

    private val covidList: MutableLiveData<CovidListModel<List<CovidStat>>> by lazy {
        MutableLiveData<CovidListModel<List<CovidStat>>>().also {
            loadStats()
        }
    }

    fun getList(): LiveData<CovidListModel<List<CovidStat>>> {
        return covidList
    }

    private fun loadStats() {
        // Do an asynchronous operation to fetch users.
        viewModelScope.launch {
            covidList.postValue(CovidListModel.loading(null))
            val covidApi: CovidAPI = ApiInstance.getCovidApi().create(CovidAPI::class.java)

            val response = covidApi.getCovidListFromApi()
            if (response == null) {
                covidList.postValue(CovidListModel.error("Error loading data", null))
            }
            val list = response.allLiveFranceData as ArrayList<CovidStat>
            list.add(0, list.removeAt(101))

            covidList.postValue(CovidListModel.success(list))
        }
    }

}