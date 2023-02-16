package com.example.themealsapp.domain.repository

import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.utils.UIState
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    fun getMealInfos(query: String): Flow<UIState<List<Meal>>>
    fun getMealInfosLocally(query: String): Flow<UIState<List<Meal>>>
}