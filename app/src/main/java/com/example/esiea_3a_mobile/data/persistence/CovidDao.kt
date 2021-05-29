package com.example.esiea_3a_mobile.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.esiea_3a_mobile.data.model.CovidStat

@Dao
interface CovidDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveStats(data: List<CovidStat>)

    @Query("SELECT * from regions")
    fun getAllStats(): LiveData<List<CovidStat>>

    @Query("SELECT * from regions LIMIT 1")
    suspend fun getOne(): CovidStat

}
