package com.example.themealsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themealsapp.data.local.entity.MealEntity

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealInfos(infos: List<MealEntity>?)

    @Query("DELETE FROM mealinfoentity WHERE strMeal IN(:strMeal)")
    suspend fun deleteMealInfos(strMeal: List<String?>?)

    @Query("SELECT * FROM mealinfoentity WHERE strMeal LIKE '%' || :strMeal || '%' ")
    suspend fun getMealInfos(strMeal: String): List<MealEntity>
}