package com.example.esiea_3a_mobile.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CovidStat(
    val nom: String,
    val deces: Int,
    val gueris: Int,
    val reanimation: Int,
    val hospitalises: Int,
    val date: String,
    val source: Source
): Parcelable

@Parcelize
data class Source(
    val nom: String
): Parcelable