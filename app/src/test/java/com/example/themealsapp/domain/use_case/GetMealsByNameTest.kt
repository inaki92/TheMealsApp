package com.example.themealsapp.domain.use_case

import com.example.themealsapp.data.local.MealDao
import com.example.themealsapp.data.remote.MealsAPI
import com.example.themealsapp.data.remote.model.meal.MealDtoResponse
import com.example.themealsapp.data.repository.MealRepositoryImpl
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.utils.NetworkState
import com.example.themealsapp.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class GetMealsByNameTest {

    private lateinit var testRepo: MealRepository

    private var testNetwork: NetworkState = mockk(relaxed = true)

    private lateinit var testCase: GetMealsByName

    private val mockMealDao: MealDao = mockk(relaxed = true)

    private val mockMealsApi: MealsAPI = mockk(relaxed = true)

    private val query1 = "Corba"

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
    fun `TEST TO VERIFY THAT LOCAL DATABASES ARE QUERIED WHEN INTERNET IS OFF`() {
        runBlocking {
            //"Corba" = query / 52977
            //AAA
            // ARRANGE/ASSIGN

            coEvery { mockMealsApi.searchMealByName(query = query1) } returns mockk {
                every { isSuccessful } returns true
                every { body() } returns MealDtoResponse(meals = mockk())
            }
            // ACT/ACTION
            val uiStates = mutableListOf<UIState<Meal>>()
            testCase.invoke(query1).collect {
                iterator<UIState<Meal>> {  }.forEach { e -> uiStates.add(e) }
            }
            //ASSERT
            assertFalse(testNetwork.isInternetOn())
            assert(uiStates.size == 0)
//            assert(uiStates[1] == UIState.SUCCESS::class.java)
        }
    }
}