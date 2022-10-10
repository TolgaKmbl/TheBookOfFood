package com.tolgakumbul.thebookoffood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class FoodDetailFragment : Fragment() {

    private var foodId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            foodId = FoodDetailFragmentArgs.fromBundle(it).foodId
            println(foodId)
        }

        val foodDetailButton = getView()?.findViewById<Button>(R.id.food_detail_button)
        foodDetailButton?.setOnClickListener {
            val action = FoodDetailFragmentDirections.actionFoodDetailFragmentToFoodListFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}