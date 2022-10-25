package com.tolgakumbul.thebookoffood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolgakumbul.thebookoffood.model.Food

class FoodListViewModel:ViewModel() {

    val foodList = MutableLiveData<List<Food>>()
    val foodIsErrorReceived = MutableLiveData<Boolean>()
    val foodIsLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        val banana = Food("Banana", "5", "6", "7","8", "test.com")
        val strawberry = Food("Strawberry", "11", "5", "1","6", "test.com.tr")

        val foods = arrayListOf(banana,strawberry)

        foodList.value = foods
        foodIsErrorReceived.value = false
        foodIsLoading.value = false
    }
}