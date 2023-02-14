package com.example.themealsapp.utils

sealed class UIState<out T> {
    data class LOADING<T>(val message: T? = null): UIState<T>()
    data class SUCCESS<T>(val response: T): UIState<T>()
    data class ERROR(val error: Exception): UIState<Nothing>()
}