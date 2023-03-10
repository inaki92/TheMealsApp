package com.example.themealsapp.data.remote

import com.example.themealsapp.data.remote.model.meal.MealDtoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsAPI {

    @GET(SEARCH)
    suspend fun searchMealByName(
        @Query("s") strMeal: String
    ): Response<MealDtoResponse>
    // www.themealdb.com/api/json/v1/1/lookup.php?i=52772
    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
        private const val FILTER = "filter.php"
        private const val SEARCH = "search.php"
        private const val LOOKUP = "lookup.php"
        private const val STARSHIPS = "list.php"
        private const val RANDOM = "random.php"
    }
}