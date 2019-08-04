package com.sixt.coding.task.di.module


import com.sixt.coding.task.api.NetworkServices
import com.sixt.coding.task.api.repo.CarsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(sixtApiService: NetworkServices) = CarsRepository(sixtApiService)

}