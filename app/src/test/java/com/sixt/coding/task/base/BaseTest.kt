package com.sixt.coding.task.base

import com.sixt.coding.task.di.DaggerTestAppComponent
import com.sixt.coding.task.di.TestAppComponent
import com.sixt.coding.task.di.TestRxJavaModule
import com.sixt.coding.task.di.factory.ViewModelFactory
import com.sixt.coding.task.di.module.NetModule
import com.sixt.coding.task.di.module.RepositoryModule
import org.junit.Before
import javax.inject.Inject
import kotlin.test.AfterTest

abstract class BaseTest {

    lateinit var testAppComponent: TestAppComponent
    @Inject lateinit var viewModelFactory: ViewModelFactory

    @Before
    open fun setUp(){
        this.configureDi()
    }

    @AfterTest
    open fun tearDown(){

    }

    // CONFIGURATION
    open fun configureDi(){
        this.testAppComponent = DaggerTestAppComponent.builder()
                .netModule(NetModule())
                .repositoryModule(RepositoryModule())
                .testRxJavaModule(TestRxJavaModule())
                .build()
        this.testAppComponent.inject(this)
    }

}