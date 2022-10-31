package com.tolgakumbul.thebookoffood.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.tolgakumbul.thebookoffood.model.Food
import com.tolgakumbul.thebookoffood.service.FoodApiService
import com.tolgakumbul.thebookoffood.service.FoodDatabase
import com.tolgakumbul.thebookoffood.util.TimeSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class FoodListViewModel(application: Application) : BaseViewModel(application) {

    val foodList = MutableLiveData<List<Food>>()
    val foodIsErrorReceived = MutableLiveData<Boolean>()
    val foodIsLoading = MutableLiveData<Boolean>()

    private val updateTime = TimeUnit.MINUTES.toNanos(10)

    private val foodApiService = FoodApiService()
    private val disposable = CompositeDisposable()
    private val timeSharedPreferences = TimeSharedPreferences(getApplication())

    fun refreshData() {
        val savedTime = timeSharedPreferences.getTime()
        if(savedTime != null && savedTime != 0L && (System.nanoTime()-savedTime < updateTime)){
            //Fetch data from SQLite
            fetchDataFromSQLite()
        }else {
            //Fetch data from API
            fetchDataFromApi()
        }
    }

    fun refreshDataRefreshLayout(){
        fetchDataFromApi()
    }

    private fun fetchDataFromSQLite(){
        foodIsLoading.value = true
        launch {
            val allFood = FoodDatabase(getApplication()).foodDao().getAllFood()
            showFood(allFood)
            Toast.makeText(getApplication(),"Fetch data from SQLite", Toast.LENGTH_SHORT).show()
        }

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
                        Toast.makeText(getApplication(),"Fetch data from Api", Toast.LENGTH_SHORT).show()
                    }
                    override fun onError(e: Throwable) {
                        foodIsErrorReceived.value = true
                        foodIsLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun showFood(foods: List<Food>) {
        foodList.value = foods
        foodIsErrorReceived.value = false
        foodIsLoading.value = false
    }

    private fun addDatabase(foods: List<Food>) {
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFood()
            val idList = dao.insertAll(*foods.toTypedArray())
            var i = 0
            while (i < foods.size) {
                foods[i].uuid = idList[i].toInt()
                i++
            }
            showFood(foods)
        }
        timeSharedPreferences.saveTime(System.nanoTime())
    }
}