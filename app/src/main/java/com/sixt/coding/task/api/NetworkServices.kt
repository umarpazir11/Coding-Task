package com.sixt.coding.task.api

import com.sixt.coding.task.model.Car
import io.reactivex.Observable
import retrofit2.http.*

interface NetworkServices {

    @get:GET("cars")
    val cars: Observable<List<Car>>
}
