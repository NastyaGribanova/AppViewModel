package com.example.coronaapp.di.component

import com.example.coronaapp.App
import com.example.coronaapp.di.module.*
import com.example.coronaapp.di.module.CountryModule
import com.example.coronaapp.di.module.CountryAllModule
import com.example.coronaapp.di.module.WorldModule
import com.example.coronaapp.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@ApplicationScope
@Component(modules = [NetModule::class, ServiceModule::class])
interface AppComponent {

    fun plusCountryComponentBuilder(): CountryComponent.Builder
    fun plusCountryAllComponentBuilder(): CountryAllComponent.Builder
    fun plusWorldComponentBuilder(): WorldComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }


}