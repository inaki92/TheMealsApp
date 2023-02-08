package com.example.themealsapp.model.ingredient


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ingredient(
    @Json(name = "idIngredient")
    val idIngredient: String? = null,
    @Json(name = "strDescription")
    val strDescription: String? = null,
    @Json(name = "strIngredient")
    val strIngredient: String? = null
)