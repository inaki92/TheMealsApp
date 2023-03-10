package com.example.themealsapp.data.local.entity

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themealsapp.data.local.MealDao
import com.example.themealsapp.data.remote.model.meal.MealDto
import com.example.themealsapp.domain.model.Meal
import com.google.gson.Gson

private const val TAG = "MealEntity"
@Entity
data class MealEntity(
    @PrimaryKey val idMeal: String,
    val strMeal: String,
    val strArea: String,
    val strCategory: String,
    val strInstructions: String,
    val strYoutube: String,
    val ingredients: String,
    val measurements: String
)

fun List<MealDto>?.mapToEntity(): List<MealEntity>? =
    this?.map {
        val tempIngredients = mutableListOf<String?>()
        tempIngredients.add(it.strIngredient1)
        tempIngredients.add(it.strIngredient2)
        tempIngredients.add(it.strIngredient3)
        tempIngredients.add(it.strIngredient4)
        tempIngredients.add(it.strIngredient5)
        tempIngredients.add(it.strIngredient6)
        tempIngredients.add(it.strIngredient7)
        tempIngredients.add(it.strIngredient8)
        tempIngredients.add(it.strIngredient9)
        tempIngredients.add(it.strIngredient9)
        tempIngredients.add(it.strIngredient10)
        tempIngredients.add(it.strIngredient11)
        tempIngredients.add(it.strIngredient12)
        tempIngredients.add(it.strIngredient13)
        tempIngredients.add(it.strIngredient14)
        tempIngredients.add(it.strIngredient15)
        tempIngredients.add(it.strIngredient16)
        tempIngredients.add(it.strIngredient17)
        tempIngredients.add(it.strIngredient18)
        tempIngredients.add(it.strIngredient19)
        tempIngredients.add(it.strIngredient20)

        var tempIngredients2 = tempIngredients.filterNotNull().toMutableList()
        tempIngredients2 = tempIngredients2.filter{x -> x != ""}.toMutableList()

        val tempMeasurements = mutableListOf<String?>()
        tempMeasurements.add(it.strMeasure1)
        tempMeasurements.add(it.strMeasure2)
        tempMeasurements.add(it.strMeasure3)
        tempMeasurements.add(it.strMeasure4)
        tempMeasurements.add(it.strMeasure5)
        tempMeasurements.add(it.strMeasure6)
        tempMeasurements.add(it.strMeasure7)
        tempMeasurements.add(it.strMeasure8)
        tempMeasurements.add(it.strMeasure9)
        tempMeasurements.add(it.strMeasure10)
        tempMeasurements.add(it.strMeasure11)
        tempMeasurements.add(it.strMeasure12)
        tempMeasurements.add(it.strMeasure13)
        tempMeasurements.add(it.strMeasure14)
        tempMeasurements.add(it.strMeasure15)
        tempMeasurements.add(it.strMeasure16)
        tempMeasurements.add(it.strMeasure17)
        tempMeasurements.add(it.strMeasure18)
        tempMeasurements.add(it.strMeasure19)
        tempMeasurements.add(it.strMeasure20)

        var tempMeasurements2 = tempMeasurements.filterNotNull().toMutableList()
        tempMeasurements2 = tempIngredients2.filter{x -> x != ""}.toMutableList()

        var tempInstructions = it.strInstructions
        tempInstructions = tempInstructions?.replace("\r\n"," ")
        val tempInstructions2 = tempInstructions?.split(". ")

        val gson = Gson()
        MealEntity(
            idMeal = it.idMeal?: "id not provided",
            strMeal = it.strMeal?: "not available",
            strArea = it.strArea?: "not available",
            strCategory = it.strCategory?: "not available",
            strInstructions = gson.toJson(tempInstructions2) ?: "",
            strYoutube = it.strYoutube?: "not available",
            ingredients = gson.toJson(tempIngredients2) ?: "",
            measurements = gson.toJson(tempMeasurements2) ?: ""
        )
    }