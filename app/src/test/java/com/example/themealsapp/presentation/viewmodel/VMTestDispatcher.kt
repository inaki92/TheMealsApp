package com.example.themealsapp.presentation.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
open class VMTestDispatcher (private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()): TestWatcher() {

        public override fun starting(description: Description) {
                super.starting(description)
                Dispatchers.setMain(dispatcher)
        }

        public override fun finished(description: Description) {
                super.finished(description)
                Dispatchers.resetMain()
        }

}