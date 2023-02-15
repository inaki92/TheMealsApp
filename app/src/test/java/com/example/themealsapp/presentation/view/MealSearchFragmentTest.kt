package com.example.themealsapp.presentation.view

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.themealsapp.data.local.MealDao
import com.example.themealsapp.data.remote.MealsAPI
import com.example.themealsapp.data.remote.model.meal.MealDtoResponse
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.domain.use_case.GetMealsByName
import com.example.themealsapp.presentation.viewmodel.MealsViewModel
import com.example.themealsapp.utils.NetworkState
import com.example.themealsapp.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class MealSearchFragmentTest {

    private lateinit var viewModelScope: CoroutineScope

    private lateinit var testVm: MealsViewModel

    private lateinit var testRepo: MealRepository

    private lateinit var testNetwork: NetworkState

    private lateinit var testCase: GetMealsByName

    private lateinit var response: LifecycleOwner

    private val mockMealDao: MealDao = mockk(relaxed = true)

    private val mockMealsApi: MealsAPI = mockk(relaxed = true)

    private val query = "Kumpir"

    private val responseCase = mutableListOf<Meal>()

    @Before
    fun setUp() {
        testCase = GetMealsByName(testRepo, testNetwork)
        testVm = MealsViewModel(testCase)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `TEST SEARCH MEALS FUNCTIONALITY FOR UISTATE AND ADAPTER UPDATEMEALS METHOD CALL`() =
        runBlocking{

            coEvery { viewModelScope.launch { testVm.meals.observe(response) {
                every { UIState.SUCCESS<List<Meal>>(responseCase) } returns mockk("foo")
            } } }


            assert(testVm.onSeach(query) == mockk("foo"))
            assert(testVm.meals == mockk("foo"))
        }
}