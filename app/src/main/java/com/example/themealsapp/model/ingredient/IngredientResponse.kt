package com.example.themealsapp.model.ingredient


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientResponse(
    @Json(name = "meals")
    val meals: List<Ingredient>? = listOf()
)