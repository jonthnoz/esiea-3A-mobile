package com.example.esiea_3a_mobile.presentation.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.esiea_3a_mobile.data.model.CovidStat
import com.example.esiea_3a_mobile.data.repository.CovidRepository
import com.example.esiea_3a_mobile.presentation.view.list.CovidListModel
import kotlinx.coroutines.launch


class CovidListViewModel(application: Application) : AndroidViewModel(application) {

    private val covidRepository = CovidRepository.getRepository(getApplication<Application>().applicationContext)

    fun getList(): LiveData<CovidListModel<List<CovidStat>>> {
        return covidRepository.getStats()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun reloadList() {
        viewModelScope.launch {
            covidRepository.refreshStats()
        }
    }
}