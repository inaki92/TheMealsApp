package com.example.themealsapp.data.repository

import com.example.themealsapp.data.local.MealDao
import com.example.themealsapp.data.remote.MealsAPI
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.utils.UIState
import kotlinx.coroutines.flow.Flow

class MealRepositoryImpl(
    private val mealsAPI: MealsAPI,
    private val mealDao: MealDao
) : MealRepository {
    override fun getMealInfos(query: String): Flow<UIState<List<Meal>>> {
        TODO("Not yet implemented")
    }

    override fun getMealInfosLocally(query: String): Flow<UIState<List<Meal>>> {
        TODO("Not yet implemented")
    }

}