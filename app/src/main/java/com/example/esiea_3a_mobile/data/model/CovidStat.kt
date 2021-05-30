package com.example.esiea_3a_mobile.data.model

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    @SerializedName("nom")
    val sourceNom: String
): Parcelable

@Parcelize
@Entity(tableName = "regions")
data class CovidStat(
    @PrimaryKey
    val nom: String,
    val deces: Int,
    val gueris: Int,
    val reanimation: Int,
    val hospitalises: Int,
    var date: String,
    @Embedded
    val source: Source,
    var initialPosition: Int?,
    var favoris: Boolean?
): Parcelable

