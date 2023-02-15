package com.example.themealsapp.utils

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.example.themealsapp.data.local.MealDao
import com.example.themealsapp.data.remote.MealsAPI
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.domain.use_case.GetMealsByName
import com.example.themealsapp.presentation.viewmodel.MealsViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import hilt_aggregated_deps._dagger_hilt_android_internal_modules_ApplicationContextModule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

//class BaseFragmentTest {
//
//    private lateinit var testVm: MealsViewModel
//
//    private lateinit var testRepo: MealRepository
//
//    private lateinit var testNetwork: NetworkState
//
//    private lateinit var testCase: GetMealsByName
//
//    private val mockMealDao: MealDao = mockk(relaxed = true)
//
//    private val mockMealsApi: MealsAPI = mockk(relaxed = true)
//
//    @Before
//    fun setUp() {
//        testCase = GetMealsByName(testRepo, testNetwork)
//        testVm = MealsViewModel(testCase)
//    }
//
//    @After
//    fun tearDown() {
//        clearAllMocks()
//    }
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun `TEST TO VERIFY ALERT ERROR DIALOG CAN BE CALLED FROM BASE FRAGMENT + CHILDREN`() =
//        runTest{
//            coEvery {  }
//        assert(testVm.viewModelScope.launch { BaseFragment().showError() }.equals(String()))
//    }
//}