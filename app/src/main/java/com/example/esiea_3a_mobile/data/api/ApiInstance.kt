package com.example.esiea_3a_mobile.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInstance {
    companion object {
        val BaseUrl = "https://coronavirusapi-france.now.sh/"

        fun getCovidApi(): Retrofit {

            return Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}