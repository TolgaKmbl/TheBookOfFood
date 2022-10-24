package com.tolgakumbul.thebookoffood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tolgakumbul.thebookoffood.R
import com.tolgakumbul.thebookoffood.model.Food

class FoodRecyclerAdapter(val foodList: ArrayList<Food>) :
    RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {/* ViewHolder Class */ }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.food_recycler_row, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.foodName).text = foodList.get(position).foodName
        holder.itemView.findViewById<TextView>(R.id.foodCalorie).text =
            foodList.get(position).foodCalorie
        //TODO: image add
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun reloadFoodList(newFoodList: ArrayList<Food>) {
        foodList.clear()
        foodList.addAll(newFoodList)
        /* Reload the adapter for all data*/
        notifyDataSetChanged()
    }
}