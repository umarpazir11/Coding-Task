package com.sixt.coding.task.di.component

import android.app.Application
import com.sixt.coding.task.base.BaseApplication
import com.sixt.coding.task.di.bulider.ActivityBuilderModule
import com.sixt.coding.task.di.module.NetModule
import com.sixt.coding.task.di.module.RepositoryModule
import com.sixt.coding.task.di.module.RxJavaModule
import com.sixt.coding.task.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,NetModule::class, ActivityBuilderModule::class,
    RepositoryModule::class, ViewModelModule::class, RxJavaModule::class])
interface AppComponent{

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: BaseApplication)
}