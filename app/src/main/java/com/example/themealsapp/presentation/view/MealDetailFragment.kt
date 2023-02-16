package com.example.themealsapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.themealsapp.R
import com.example.themealsapp.databinding.FragmentMealDetailBinding
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.utils.BaseFragment


class MealDetailFragment : BaseFragment() {

    val binding by lazy {
        FragmentMealDetailBinding.inflate(layoutInflater)
    }

    private lateinit var selectedItem: Meal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        selectedItem = mealsViewModel.selectedMealItem
        binding.mealImage.setImageResource(R.drawable.baseline_fastfood_24)
        binding.mealTitle.text = selectedItem.strMeal

        Glide
            .with(binding.root)
            .load(selectedItem.strMealThumb)
            .centerCrop()
            .placeholder(R.drawable.baseline_fastfood_24)
            .error(R.drawable.baseline_no_food_24)
            .into(binding.mealImage)
        return inflater.inflate(R.layout.fragment_meal_detail, container, false)
    }
}