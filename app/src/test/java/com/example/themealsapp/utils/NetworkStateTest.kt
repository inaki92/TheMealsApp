package com.example.themealsapp.utils

import com.example.themealsapp.data.local.MealDao
import com.example.themealsapp.data.remote.MealsAPI
import com.example.themealsapp.data.repository.MealRepositoryImpl
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.domain.use_case.GetMealsByName
import io.mockk.clearAllMocks
import io.mockk.mockk
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class NetworkStateTest {

    private lateinit var testRepo: MealRepository

    private lateinit var testNetwork: NetworkState

    private lateinit var testCase: GetMealsByName

    private val mockMealDao: MealDao = mockk(relaxed = true)

    private val mockMealsApi: MealsAPI = mockk(relaxed = true)

    @Before
    fun setUp() {
        testRepo = MealRepositoryImpl(mockMealsApi, mockMealDao)
        testCase = GetMealsByName(testRepo, testNetwork)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `TEST TO VERIFY THAT INTERNET IS OFF`() {
        assertFalse(!testNetwork.isInternetOn())
    }
}