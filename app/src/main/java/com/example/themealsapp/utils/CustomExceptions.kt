package com.example.themealsapp.utils

class NullMealsResponse(message: String = "Meals response is null") : Exception(message)
class FailureResponse(message: String?) : Exception(message)