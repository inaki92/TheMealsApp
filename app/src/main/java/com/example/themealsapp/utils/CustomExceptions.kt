package com.example.themealsapp.utils

class NullMealsResponse(message: String = "Schools response is null") : Exception(message)
class FailureResponse(message: String?) : Exception(message)