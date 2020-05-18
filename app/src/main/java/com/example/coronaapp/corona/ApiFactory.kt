package com.example.coronaapp.corona

import com.example.coronaapp.BuildConfig
import dagger.Provides

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object ApiFactory {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

//    @Provides
//    @Singleton
//    fun coronaService(): CoronaService {
//        val coronaService: CoronaService by lazy {
//            retrofit.create(CoronaService::class.java)
//        }
//        return coronaService
//    }
}