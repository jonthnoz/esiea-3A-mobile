package com.example.esiea_3a_mobile.data.api

import com.example.esiea_3a_mobile.data.model.CovidResponse
import retrofit2.http.GET

public interface CovidAPI {

    @GET("AllLiveData")
    suspend fun getCovidListFromApi(): CovidResponse
}

