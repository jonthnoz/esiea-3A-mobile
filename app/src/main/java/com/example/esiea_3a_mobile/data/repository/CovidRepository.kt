package com.example.esiea_3a_mobile.data.repository

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_VALIDATED
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.esiea_3a_mobile.data.api.ApiInstance
import com.example.esiea_3a_mobile.data.api.CovidAPI
import com.example.esiea_3a_mobile.data.model.CovidStat
import com.example.esiea_3a_mobile.data.persistence.CovidDatabase
import com.example.esiea_3a_mobile.presentation.view.list.CovidListModel
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class CovidRepository (context: Context) {

    private val covidDb = CovidDatabase.getDatabase(context)
    private val covidApi = ApiInstance.getCovidApi().create(CovidAPI::class.java)
    private val resourceStatus = MediatorLiveData<CovidListModel<List<CovidStat>>>()

    private val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    companion object {

        @Volatile
        private var INSTANCE: CovidRepository? = null

        fun getRepository(context: Context): CovidRepository {

            return INSTANCE ?: synchronized(this) {
                val instance = CovidRepository(context)
                INSTANCE = instance

                instance
            }
        }
    }

        init {
            resourceStatus.addSource(covidDb.covidDao().getAllStats()) {
                if (it.isNotEmpty()) resourceStatus.postValue(CovidListModel.success(it))
            }
        }

        fun getStats(): LiveData<CovidListModel<List<CovidStat>>> {
            return resourceStatus
        }

        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun refreshStats() {

                if (shouldFetch()) {
                    // Refreshes the data.
                    resourceStatus.postValue(CovidListModel.loading(null))

                    try {
                        val response = covidApi.getCovidListFromApi()

                        val list = response.allLiveFranceData as ArrayList<CovidStat>
                        list.add(0, list.removeAt(101))
                        for (region: CovidStat in list) {
                            region.date = LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        }

                        covidDb.covidDao().saveStats(list)
                    }
                    catch (e: Exception) {
                        resourceStatus.postValue(CovidListModel.error("Error loading data:" + e.message, null))
                    }
                }
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun shouldFetch(): Boolean {

        val network = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (network == null || !network.hasCapability(NET_CAPABILITY_VALIDATED)){
            resourceStatus.postValue(CovidListModel.error("Pas d'accès internet", null))
            return false
        }

        if (resourceStatus.value==null) {
            return true
        }

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        val dateSavedString = covidDb.covidDao().getOne().date
        val daySaved = dateSavedString.substring(8, 10).toInt()
        val dateSaved = ZonedDateTime.parse(dateSavedString, formatter.withZone(ZoneId.of("Europe/Paris")))

        val currentTimeString = LocalDateTime.now().format(formatter)
        val today = currentTimeString.substring(8,10).toInt()

        val limitString = currentTimeString.substring(0, 11) + "20:05:00"
        val limit = ZonedDateTime.parse(limitString, formatter.withZone(ZoneId.of("Europe/Paris")))

        return (dateSaved.isAfter(limit) && today - daySaved > 0) || today - daySaved > 1
    }
}