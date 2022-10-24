package com.tolgakumbul.thebookoffood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolgakumbul.thebookoffood.model.Food

class FoodListViewModel:ViewModel() {

    val foodList = MutableLiveData<List<Food>>()
    val foodIsErrorReceived = MutableLiveData<Boolean>()
    val foodIsLoading = MutableLiveData<Boolean>()
}