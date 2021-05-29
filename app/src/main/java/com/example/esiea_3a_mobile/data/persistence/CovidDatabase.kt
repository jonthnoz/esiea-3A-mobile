package com.example.esiea_3a_mobile.data.persistence

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.esiea_3a_mobile.data.model.CovidStat

@Database(entities = [CovidStat::class], version = 1)
abstract class CovidDatabase : RoomDatabase() {

    abstract fun covidDao(): CovidDao

    companion object {

        @Volatile
        private var INSTANCE: CovidDatabase? = null

        fun getDatabase(context: Context): CovidDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CovidDatabase::class.java,
                    "regions_db"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}
