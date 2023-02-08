package com.example.themealsapp.model.category


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    @Json(name = "categories")
    val categories: List<Category>? = listOf()
)