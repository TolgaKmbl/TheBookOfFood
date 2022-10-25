package com.tolgakumbul.thebookoffood.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tolgakumbul.thebookoffood.R
import com.tolgakumbul.thebookoffood.adapter.FoodRecyclerAdapter
import com.tolgakumbul.thebookoffood.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.fragment_food_list.*


class FoodListFragment : Fragment() {

    private lateinit var viewModel: FoodListViewModel
    private val recyclerFoodAdapter = FoodRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FoodListViewModel::class.java)
        viewModel.refreshData()

        val foodListRecyclerView = view.findViewById(R.id.foodListRecyclerView) as RecyclerView
        foodListRecyclerView.layoutManager = LinearLayoutManager(context)
        foodListRecyclerView.adapter = recyclerFoodAdapter
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.foodList.observe(viewLifecycleOwner, Observer {
            it?.let {
                foodListRecyclerView.visibility = View.VISIBLE
                recyclerFoodAdapter.reloadFoodList(it)
            }
        })
        viewModel.foodIsErrorReceived.observe(viewLifecycleOwner, Observer {
            it?.let {

                val foodListErrorMsg = view?.findViewById(R.id.foodListErrorMsg) as TextView
                if (it) {
                    foodListRecyclerView.visibility = View.GONE
                    foodListErrorMsg.visibility = View.VISIBLE
                } else {
                    foodListErrorMsg.visibility = View.GONE
                    foodListRecyclerView.visibility = View.VISIBLE
                }

            }
        })
        viewModel.foodIsLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                val foodListProgressBar =
                    view?.findViewById(R.id.foodListProgressBar) as ProgressBar
                if (it) {
                    foodListRecyclerView.visibility = View.GONE
                    foodListErrorMsg.visibility = View.GONE
                    foodListProgressBar.visibility = View.VISIBLE
                } else {
                    foodListProgressBar.visibility = View.GONE
                }

            }
        })
    }

}