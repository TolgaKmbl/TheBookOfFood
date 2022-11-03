package com.tolgakumbul.thebookoffood.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tolgakumbul.thebookoffood.R
import com.tolgakumbul.thebookoffood.databinding.FragmentFoodDetailBinding
import com.tolgakumbul.thebookoffood.viewmodel.FoodDetailViewModel

class FoodDetailFragment : Fragment() {

    private lateinit var viewModel: FoodDetailViewModel
    private var foodId: Int? = null
    private lateinit var dataBinding : FragmentFoodDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_detail,container,false)
        return dataBinding.root;
        /*return inflater.inflate(R.layout.fragment_food_detail, container, false)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            foodId = FoodDetailFragmentArgs.fromBundle(it).foodId
        }
        viewModel = ViewModelProviders.of(this).get(FoodDetailViewModel::class.java)
        viewModel.fetchData(foodId)
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.foodDetail.observe(viewLifecycleOwner, Observer { food ->
            food?.let {
                dataBinding.foodDetail = it
                /*val foodDetailName = view?.findViewById(R.id.foodDetailName) as TextView
                foodDetailName.text = it.foodName
                val foodCalorie = view?.findViewById(R.id.foodDetailCalorie) as TextView
                foodCalorie.text = it.foodCalorie
                val foodCarbohydrate = view?.findViewById(R.id.foodDetailCarbohydrate) as TextView
                foodCarbohydrate.text = it.foodCarbohydrate
                val foodDetailProtein = view?.findViewById(R.id.foodDetailProtein) as TextView
                foodDetailProtein.text = it.foodProtein
                val foodDetailFat = view?.findViewById(R.id.foodDetailFat) as TextView
                foodDetailFat.text = it.foodFat
                val foodDetailImage = view?.findViewById(R.id.foodDetailImage) as ImageView
                context?.let {
                    foodDetailImage.downloadImg(food.foodImage, placeHolderFactory(it))
                }*/
            }
        })
    }

}