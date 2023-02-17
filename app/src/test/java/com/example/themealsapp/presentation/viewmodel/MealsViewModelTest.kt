package com.example.themealsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.themealsapp.data.local.MealDao
import com.example.themealsapp.data.remote.MealsAPI
import com.example.themealsapp.data.remote.model.meal.MealDtoResponse
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.domain.use_case.GetMealsByName
import com.example.themealsapp.utils.NetworkState
import com.example.themealsapp.utils.UIState
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.Description
import java.util.concurrent.Executor


class MealsViewModelTest {

    private var viewModelScope: CoroutineScope = mockk(relaxed = true)

    private var testVm: MealsViewModel = mockk(relaxed = true)

    private var testRepo: MealRepository = mockk(relaxed = true)

    private var testNetwork: NetworkState  = mockk(relaxed = true)

    private var testCase: GetMealsByName = mockk(relaxed = true)

    private var response: MealDtoResponse = mockk(relaxed = true)

    val mockMealsByName = mockk<GetMealsByName>(relaxed = true)

    val mockOnSeach = testVm.onSeach(String())

    private val mockMealDao: MealDao = mockk(relaxed = true)

    private val mockMealsApi: MealsAPI = mockk(relaxed = true)

    private val query = "Corba"

    private val responseCase = mutableListOf<Meal>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        every {
        testCase = GetMealsByName(testRepo, testNetwork)
        testVm = MealsViewModel(testCase)
        vmTestDispatcher.starting(description = Description.createTestDescription("SET UP VMTEST", "MEALSVIEWMODEL"))
    }}

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        vmTestDispatcher.finished(description = Description.createTestDescription("TEAR DOWN VMTEST", "MEALSVIEWMODEL"))
        clearAllMocks()
    }

    @ExperimentalCoroutinesApi
    @get: Rule
    val vmTestDispatcher = VMTestDispatcher()
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `TEST SEARCH METHODS FOR MEALSVIEWMODEL - IMPLEMENTS GETMEALSBYNAME WITH QUERY`() =
        runTest{

            coEvery {
                viewModelScope.launch(viewModelScope.coroutineContext, CoroutineStart.LAZY) { testCase(query).collect {
                every { UIState.SUCCESS<List<Meal>>(responseCase) } returns mockk("foo")
            }  } }

        assert(testVm.onSeach(query) == mockk("foo"))
        assert(testVm.meals == mockk("foo"))
    }
}