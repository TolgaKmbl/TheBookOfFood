package com.tolgakumbul.thebookoffood.service

import com.tolgakumbul.thebookoffood.model.Food
import io.reactivex.Single
import retrofit2.http.GET

interface FoodApi {

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getFoods(): Single<List<Food>>

}