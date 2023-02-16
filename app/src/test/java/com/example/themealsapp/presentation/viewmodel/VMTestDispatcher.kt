package com.example.themealsapp.presentation.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class VMTestDispatcher (private val dispatcher: TestDispatcher = StandardTestDispatcher()): TestWatcher() {

        override fun starting(description: Description) = Dispatchers.setMain(dispatcher)

        override fun finished(description: Description) = Dispatchers.resetMain()

}