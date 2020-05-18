package com.example.coronaapp.di.module

import com.example.coronaapp.App
import com.example.coronaapp.corona.CoronaService
import com.example.coronaapp.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {

    @Provides
    @ApplicationScope
    fun provideCoronaService():
            CoronaService = App.netComponent.getRetrofit().create(CoronaService::class.java)
}