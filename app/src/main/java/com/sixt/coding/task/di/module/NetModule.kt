package com.sixt.coding.task.di.module


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sixt.coding.task.api.NetworkServices
import com.sixt.coding.task.utils.BASE_URL
import com.sixt.coding.task.utils.TIMEOUT_REQUEST
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {

    private val interceptor = HttpLoggingInterceptor()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideRetrofit(client: OkHttpClient, gson: Gson) = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(client)

    @Provides
    @Singleton
    fun provideRetrofitService(builder: Retrofit.Builder): NetworkServices = builder.baseUrl(BASE_URL).build().create(NetworkServices::class.java)

}