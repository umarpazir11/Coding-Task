package com.sixt.coding.task.di

import com.sixt.coding.task.base.BaseTest
import com.sixt.coding.task.di.module.NetModule
import com.sixt.coding.task.di.module.RepositoryModule
import com.sixt.coding.task.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class, RepositoryModule::class, ViewModelModule::class, TestRxJavaModule::class])
interface TestAppComponent {
    fun inject(baseTest: BaseTest)
}