package com.sixt.coding.task.api.repo

import com.sixt.coding.task.api.NetworkServices

class CarsRepository(private val sixtApiService: NetworkServices) {

    fun getCars() = this.sixtApiService.cars!!

}