package com.sixt.coding.task.di.bulider


import com.sixt.coding.task.carslist.CarFragment
import com.sixt.coding.task.carsmap.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun mapFragment(): MapFragment

    @ContributesAndroidInjector
    internal abstract fun carFragment(): CarFragment

}
