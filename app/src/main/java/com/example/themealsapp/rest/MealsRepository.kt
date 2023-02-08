package com.example.themealsapp.rest

import com.example.themealsapp.model.meal.MealResponse
import com.example.themealsapp.utils.UIState
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    /**
     * Get a meal by providing its ID
     */
    fun getMealById(i : String): Flow<UIState<MealResponse>>
}

class MealsRepositoryImpl() : MealsRepository {
    override fun getMealById(i : String): Flow<UIState<MealResponse>> {
        TODO("Not yet implemented")
    }
}