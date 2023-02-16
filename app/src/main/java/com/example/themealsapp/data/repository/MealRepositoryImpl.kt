package com.example.themealsapp.data.repository

import android.util.Log
import com.example.themealsapp.data.local.MealDao
import com.example.themealsapp.data.local.entity.mapToEntity
import com.example.themealsapp.data.remote.MealsAPI
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.model.MealFiltered
import com.example.themealsapp.domain.model.mapToMeal
import com.example.themealsapp.domain.model.mapToMealFiltered
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.utils.FailureResponse
import com.example.themealsapp.utils.NullResponse
import com.example.themealsapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "MealRepositoryImpl"
class MealRepositoryImpl @Inject constructor(
    private val mealsAPI: MealsAPI,
    private val mealDao: MealDao
) : MealRepository {

    override fun getMealInfos(strMeal: String): Flow<UIState<List<Meal>>> = flow {
        emit(UIState.LOADING)

        try {
            Log.d(TAG, "getMealInfos: Fetching data from API")
            val response = mealsAPI.searchMealByName(strMeal)
            if (response.isSuccessful) {
                response.body()?.let { mealsResponse ->
                    val mealInfos = mealsResponse.meals
                    mealDao.insertMeals(mealInfos.mapToEntity())
                    val newMealsInfos = mealDao.getMealsByName(strMeal)
                    emit(UIState.SUCCESS(newMealsInfos.mapToMeal()))
                } ?: throw NullResponse()
            } else throw FailureResponse(response.errorBody()?.string())
        } catch (e: Exception) {
            Log.e(TAG, "getMealInfos: ${e}", )
            emit(UIState.ERROR(e))
        }
    }

    override fun getMealInfosLocally(strMeal: String): Flow<UIState<List<Meal>>> = flow {
        try {
            Log.d(TAG, "getMealInfosLocally: Fetching data from local database")
            val mealInfos = mealDao.getMealsByName(strMeal)
            emit(UIState.SUCCESS(mealInfos.mapToMeal()))
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

    override fun getFilteredMealByArea(strArea: String): Flow<UIState<List<MealFiltered>>> = flow {
        emit(UIState.LOADING)

        try {
            Log.d(TAG, "getMealInfos: Fetching data from API")
            val response = mealsAPI.filterRandomMealsByArea(strArea)
            if (response.isSuccessful) {
                response.body()?.let { mealsResponse ->
                    val mealInfos = mealsResponse.meals.mapToMealFiltered()
                    emit(UIState.SUCCESS(mealInfos?: emptyList()))
                } ?: throw NullResponse()
            } else throw FailureResponse(response.errorBody()?.string())
        } catch (e: Exception) {
            Log.e(TAG, "getMealInfos: ${e}", )
            emit(UIState.ERROR(e))
        }
    }


}