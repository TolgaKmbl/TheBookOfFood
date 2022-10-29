package com.tolgakumbul.thebookoffood.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tolgakumbul.thebookoffood.model.Food

@Database(entities = arrayOf(Food::class), version = 1)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDAO

    companion object {

        @Volatile
        private var instance: FoodDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: getDb(context).also {
                instance = it
            }
        }

        private fun getDb(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FoodDatabase::class.java,
            "FoodDatabase"
        ).build()
    }


}