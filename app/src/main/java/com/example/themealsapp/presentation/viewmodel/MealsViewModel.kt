package com.example.themealsapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.use_case.GetMealsByName
import com.example.themealsapp.utils.NetworkState
import com.example.themealsapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

private const val TAG = "MealsViewModel"
@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealsByName: GetMealsByName,
    private val networkState: NetworkState
): ViewModel(){
    fun getNetworkState(): Boolean{
        return networkState.isInternetOn()
    }
    init {
        onSearchMealsByName()
    }

    private val _meals : MutableLiveData<UIState<List<Meal>>> = MutableLiveData(UIState.LOADING())
    val meals : MutableLiveData<UIState<List<Meal>>> get() = _meals
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    fun onSearchMealsByName(strMeal: String = "") {
        Log.d(TAG, "onSearchMealsByName: Entered in the ViewModel")
        viewModelScope.launch {
            getMealsByName(strMeal).collect { result ->
                _meals.postValue(result)
            }
        }
    }
}