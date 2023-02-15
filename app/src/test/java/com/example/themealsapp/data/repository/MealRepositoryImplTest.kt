package com.example.themealsapp.data.repository

import com.example.themealsapp.data.local.MealDao
import com.example.themealsapp.data.remote.MealsAPI
import com.example.themealsapp.data.remote.model.meal.MealDtoResponse
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.domain.use_case.GetMealsByName
import com.example.themealsapp.utils.NetworkState
import com.example.themealsapp.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class MealRepositoryImplTest {

    private lateinit var testRepo : MealRepository

    private lateinit var testNetwork : NetworkState

    private lateinit var testCase2 : GetMealsByName

    private val mockMealDao: MealDao = mockk(relaxed = true)

    private val mockMealsApi: MealsAPI = mockk(relaxed = true)

    private val query1 = "Kumpir"
//    private val query2 = "Corba"

    @Before
    fun setUp() {
        testRepo = MealRepositoryImpl(mockMealsApi, mockMealDao)
        testCase2 = GetMealsByName(testRepo, testNetwork)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    suspend fun `TEST TO CONFIRM STRING MEAL NAME WILL RETRIEVE DATABASE INFO`() =
    runBlocking{
        //"Kumpir" = query / 52978
        //AAA
        // ARRANGE/ASSIGN
        coEvery { mockMealsApi.searchMealByName(query = query1) } returns mockk {
                every { isSuccessful } returns true
                every { body() } returns MealDtoResponse(meals = mockk())
        }

        // ACT/ACTION
        val uiStates = mutableListOf<UIState<Meal>>()
        testRepo.getMealInfos(query1).collect {
            iterator<UIState<Meal>> { }.forEach {e -> uiStates.add(e) }
        }
        //ASSERT
        assert(uiStates.size == 2)
        assert(uiStates[1] == UIState.SUCCESS::class.java)
    }


    }