package com.tolgakumbul.thebookoffood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolgakumbul.thebookoffood.model.Food
import com.tolgakumbul.thebookoffood.service.FoodApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FoodListViewModel : ViewModel() {

    val foodList = MutableLiveData<List<Food>>()
    val foodIsErrorReceived = MutableLiveData<Boolean>()
    val foodIsLoading = MutableLiveData<Boolean>()

    private val foodApiService = FoodApiService()
    private val disposable = CompositeDisposable()

    fun refreshData() {
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        foodIsLoading.value = true

        disposable.add(
            foodApiService
                .getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Food>>() {
                    override fun onSuccess(t: List<Food>) {
                        addDatabase(t)
                    }

                    override fun onError(e: Throwable) {
                        foodIsErrorReceived.value = true
                        foodIsLoading.value = false
                        e.printStackTrace()
                    }

                })

        )
    }

    private fun showFood(foods: List<Food>){
        foodList.value = foods
        foodIsErrorReceived.value = false
        foodIsLoading.value = false
    }

    private fun addDatabase(foods: List<Food>){

    }
}