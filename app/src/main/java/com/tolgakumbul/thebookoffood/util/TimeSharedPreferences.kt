package com.tolgakumbul.thebookoffood.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class TimeSharedPreferences {
    companion object{
        private var sharedPreferences: SharedPreferences? = null

        @Volatile private var instance : TimeSharedPreferences? = null

        private val lock = Any()
        operator fun invoke(context: Context): TimeSharedPreferences = instance ?: synchronized(lock){
            instance ?: timeSharedPreferencesFactory(context).also {
                instance = it
            }
        }

        private fun timeSharedPreferencesFactory(context: Context):TimeSharedPreferences{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return TimeSharedPreferences()
        }
    }

    fun saveTime(time:Long){
        sharedPreferences?.edit(commit = true){
            putLong("time", time)
        }
    }

    fun getTime() = sharedPreferences?.getLong("time", 0)
}