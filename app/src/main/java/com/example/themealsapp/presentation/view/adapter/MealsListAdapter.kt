package com.example.themealsapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themealsapp.databinding.MealsItemBinding
import com.example.themealsapp.domain.model.Meal

class MealsListAdapter(
    private val itemSet: MutableList<Meal> = mutableListOf(),
    private val onItemClick: (item: Meal) -> Unit
): RecyclerView.Adapter<MealSearchViewHolder>() {

    fun updateMeals(newItems: List<Meal>) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealSearchViewHolder =
        MealSearchViewHolder(
            MealsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = itemSet.size

    override fun onBindViewHolder(holder: MealSearchViewHolder, position: Int) {
        holder.bind(itemSet[position], onItemClick)
    }

}


class MealSearchViewHolder(
    private val binding: MealsItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Meal, onItemClick: (Meal) -> Unit) {
        binding.mealName.text = item.strMeal

        itemView.setOnClickListener {
            item.let { onItemClick }
        }
    }
}