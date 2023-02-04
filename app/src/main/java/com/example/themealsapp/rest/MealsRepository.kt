package com.example.themealsapp.rest

import com.example.themealsapp.model.MealResponse
import com.example.themealsapp.utils.UIState
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    /**
     * Get a meal by providing its ID
     */
    fun getMealById(): Flow<UIState<MealResponse>>
}

class MealsRepositoryImpl() : MealsRepository {
    override fun getMealById(): Flow<UIState<MealResponse>> {
        TODO("Not yet implemented")
    }
}