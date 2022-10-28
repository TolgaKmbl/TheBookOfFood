package com.tolgakumbul.thebookoffood.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tolgakumbul.thebookoffood.model.Food

@Dao
interface FoodDAO {

    @Insert
    suspend fun insertAll(vararg food: Food): List<Long>

    @Query("SELECT * FROM food")
    suspend fun getAllFood(): List<Food>

    @Query("SELECT * FROM food WHERE uuid = :id")
    suspend fun getFoodById(id: Int): Food

    @Query("DELETE FROM food WHERE uuid = :id")
    suspend fun deleteFoodById(id: Int)

    @Query("DELETE FROM food")
    suspend fun deleteAllFood()
}