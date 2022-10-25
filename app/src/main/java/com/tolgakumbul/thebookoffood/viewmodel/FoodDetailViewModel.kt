package com.tolgakumbul.thebookoffood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolgakumbul.thebookoffood.model.Food

class FoodDetailViewModel: ViewModel()  {

    val foodDetail = MutableLiveData<Food>()

    fun fetchData(){
        val banana = Food("Banana", "5", "6", "7","8", "test.com")

        foodDetail.value = banana
    }
}