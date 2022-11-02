package com.tolgakumbul.thebookoffood.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.tolgakumbul.thebookoffood.model.Food
import com.tolgakumbul.thebookoffood.service.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetailViewModel(application: Application) : BaseViewModel(application) {

    val foodDetail = MutableLiveData<Food>()

    fun fetchData(id: Int?) {
        launch {
            if(id != null){
                foodDetail.value = FoodDatabase(getApplication()).foodDao().getFoodById(id)
            }
        }
    }
}