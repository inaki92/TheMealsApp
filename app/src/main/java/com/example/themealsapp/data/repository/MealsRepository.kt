package com.example.themealsapp.data.repository

import com.example.themealsapp.data.local.MealDao
import com.example.themealsapp.data.remote.model.meal.MealDtoResponse
import com.example.themealsapp.data.remote.MealsAPI
import com.example.themealsapp.utils.UIState
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    /**
     * Get a meal by providing its ID
     */
    fun getMealById(i : String): Flow<UIState<MealDtoResponse>>
}

class MealsRepositoryImpl(
    private val mealsAPI: MealsAPI,
    private val mealDao: MealDao
) : MealsRepository {
    override fun getMealById(i : String): Flow<UIState<MealDtoResponse>> {
        TODO("Not yet implemented")
    }
}