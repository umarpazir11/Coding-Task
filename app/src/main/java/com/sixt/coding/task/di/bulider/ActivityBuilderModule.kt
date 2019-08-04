package com.sixt.coding.task.di.bulider


import com.sixt.coding.task.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    internal abstract fun mapsActivity(): HomeActivity



}
