package com.tolgakumbul.thebookoffood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tolgakumbul.thebookoffood.R
import com.tolgakumbul.thebookoffood.databinding.FoodRecyclerRowBinding
import com.tolgakumbul.thebookoffood.model.Food
import com.tolgakumbul.thebookoffood.view.FoodListFragmentDirections
import kotlinx.android.synthetic.main.food_recycler_row.view.*

class FoodRecyclerAdapter(val foodList: ArrayList<Food>) :
    RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder>(), FoodClickListener {

    class FoodViewHolder(var view: FoodRecyclerRowBinding) :
        RecyclerView.ViewHolder(view.root) {/* ViewHolder Class */ }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.food_recycler_row, parent, false)
        val view = DataBindingUtil.inflate<FoodRecyclerRowBinding>(inflater, R.layout.food_recycler_row, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.view.food = foodList[position]
        holder.view.listener = this
        /*holder.itemView.findViewById<TextView>(R.id.foodName).text = foodList.get(position).foodName
        holder.itemView.findViewById<TextView>(R.id.foodCalorie).text =
            foodList.get(position).foodCalorie
        holder.itemView.findViewById<ImageView>(R.id.imageView).downloadImg(
            foodList.get(position).foodImage,
            placeHolderFactory(holder.itemView.context)
        )

        holder.itemView.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment()
            action.foodId = foodList.get(position).uuid
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun reloadFoodList(newFoodList: List<Food>) {
        foodList.clear()
        foodList.addAll(newFoodList)
        /* Reload the adapter for all data*/
        notifyDataSetChanged()
    }

    override fun foodClicked(view: View) {
        val uuid = view.foodUUID.text.toString().toIntOrNull()
        uuid?.let {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment()
            action.foodId = it
            Navigation.findNavController(view).navigate(action)
        }
    }
}