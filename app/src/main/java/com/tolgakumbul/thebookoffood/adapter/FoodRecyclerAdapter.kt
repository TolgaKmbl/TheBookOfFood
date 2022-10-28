package com.tolgakumbul.thebookoffood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tolgakumbul.thebookoffood.R
import com.tolgakumbul.thebookoffood.model.Food
import com.tolgakumbul.thebookoffood.util.downloadImg
import com.tolgakumbul.thebookoffood.util.placeHolderFactory
import com.tolgakumbul.thebookoffood.view.FoodListFragmentDirections

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
        holder.itemView.findViewById<ImageView>(R.id.imageView).downloadImg(
            foodList.get(position).foodImage,
            placeHolderFactory(holder.itemView.context)
        )

        holder.itemView.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment()
            action.foodId = 0
            Navigation.findNavController(it).navigate(action)
        }
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
}