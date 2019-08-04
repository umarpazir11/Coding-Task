package com.sixt.coding.task

import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.sixt.coding.task.base.BaseTest
import com.sixt.coding.task.carslist.CarsListViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner::class)
class CarsUnitTest : BaseTest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Force tests to be executed synchronously

    // FOR DATA
    private lateinit var activity: FragmentActivity
    private lateinit var viewModel: CarsListViewModel

    @Before
    override fun setUp(){
        super.setUp()
        this.activity = Robolectric.setupActivity(FragmentActivity::class.java)
        this.viewModel = ViewModelProviders.of(this.activity, viewModelFactory)[CarsListViewModel::class.java]
    }

    @Test
    fun getCars_whenSuccess() {
        // Pre-test
//        assertEquals(null, this.viewModel.cars.value, "User should be null because stream not started yet")
        // Execute View Model
        this.viewModel.getCars()
        // Checks
        assertNotEquals(0, this.viewModel.cars.value?.size, "Cars must not be zero")
        assertEquals(false, this.viewModel.isLoading.value, "Should be reset to 'false' because stream ended")
        assertEquals(null, this.viewModel.errorMessage.value, "No error must be founded")
    }
}