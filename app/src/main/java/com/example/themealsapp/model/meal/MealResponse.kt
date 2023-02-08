package com.example.themealsapp.model.meal


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealResponse(
    @Json(name = "meals")
    val meals: List<Meal>? = listOf()
)