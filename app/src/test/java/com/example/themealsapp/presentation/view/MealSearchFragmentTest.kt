package com.example.themealsapp.presentation.view

import androidx.lifecycle.LifecycleOwner
import com.example.themealsapp.data.local.MealDao
import com.example.themealsapp.data.remote.MealsAPI
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.domain.use_case.GetMealsByName
import com.example.themealsapp.presentation.view.adapter.MSFTestDispatcher
import com.example.themealsapp.presentation.viewmodel.MealsViewModel
import com.example.themealsapp.presentation.viewmodel.VMTestDispatcher
import com.example.themealsapp.utils.NetworkState
import com.example.themealsapp.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.Description

class MealSearchFragmentTest {

    private var viewModelScope: CoroutineScope = mockk(relaxed = true)

    private lateinit var testVm: MealsViewModel

    private var testRepo: MealRepository  = mockk(relaxed = true)

    private var testNetwork: NetworkState  = mockk(relaxed = true)

    private lateinit var testCase: GetMealsByName

    private lateinit var response: LifecycleOwner

    private val mockMealDao: MealDao = mockk(relaxed = true)

    private val mockMealsApi: MealsAPI = mockk(relaxed = true)

    private val query = "Kumpir"

    private val responseCase = mutableListOf<Meal>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        testCase = GetMealsByName(testRepo, testNetwork)
        testVm = MealsViewModel(testCase)
        msfTestDispatcher.starting(description = Description.createTestDescription("SET UP MEALSEARCHTEST", "MEALSEARCHFRAGMENT"))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        msfTestDispatcher.finished(description = Description.createTestDescription("TEAR DOWN MEALSEARCHTEST", "MEALSEARCHFRAGMENT"))
        clearAllMocks()
    }

    @ExperimentalCoroutinesApi
    @get: Rule
    val msfTestDispatcher = MSFTestDispatcher()
    @OptIn(ExperimentalCoroutinesApi::class, InternalCoroutinesApi::class)
    @Test
    fun `TEST SEARCH MEALS FUNCTIONALITY FOR UISTATE AND ADAPTER UPDATEMEALS METHOD CALL`() =
        runTest{

            coEvery { viewModelScope.launch(viewModelScope.coroutineContext, CoroutineStart.LAZY) { testVm.meals.observe(response) {
                every { UIState.SUCCESS<List<Meal>>(responseCase) } returns mockk("foo")
            } } }
            assert(testVm.onSeach(query) == mockk("foo"))
            assert(testVm.meals == mockk("foo"))
        }
}