package com.example.esiea_3a_mobile.data.model

data class CovidStat(
    val nom: String?,
    val deces: Int?,
    val gueris: Int?,
    val reanimation: Int?,
    val date: String?,
    val source: Source?
)

data class Source(
    val nom: String
)