package com.example.themealsapp.rest

import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.utils.UIState
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    /**
     * Get a meal by providing its ID
     */
    fun getMealById(i : String): Flow<UIState<Meal>>
}

class MealsRepositoryImpl() : MealsRepository {
    override fun getMealById(i : String): Flow<UIState<Meal>> {
        TODO("Not yet implemented")
    }
}