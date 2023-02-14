package com.example.themealsapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.use_case.GetMealsByName
import com.example.themealsapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val TAG = "MealsViewModel"
@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealsByName: GetMealsByName
): ViewModel(){

    init {
        onSeach("")
    }

    private val _meals : MutableLiveData<UIState<List<Meal>>> = MutableLiveData(UIState.LOADING())
    val meals : MutableLiveData<UIState<List<Meal>>> get() = _meals


    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    fun onSeach(query: String) {
        Log.d(TAG, "onSeach: Entered in the ViewModel")
        viewModelScope.launch {
            getMealsByName(query).collect() { result ->
                when (result) {
                    is UIState.LOADING -> {}
                    is UIState.SUCCESS<List<Meal>> -> {
                        _meals.postValue(result)
                    }
                    is UIState.ERROR -> {

                    }
                }

            }
        }
    }

    sealed class UIEvent {
        data class showSnackbar(val message : String): UIEvent()

    }
}