package com.example.themealsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themealsapp.data.local.entity.MealEntity

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(infos: List<MealEntity>?)

    @Query("SELECT * FROM mealentity WHERE strMeal LIKE '%' || :strMeal || '%' ")
    suspend fun getMealsByName(strMeal: String): List<MealEntity>
}