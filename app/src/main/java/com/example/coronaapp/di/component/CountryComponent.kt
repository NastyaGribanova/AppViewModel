package com.example.coronaapp.di.component

import com.example.coronaapp.CountryActivity
import com.example.coronaapp.di.scope.ActivityScope
import com.example.coronaapp.di.module.CountryModule
import com.example.coronaapp.di.module.ViewModelModule
import com.example.coronaapp.di.scope.CountryScope
import dagger.Subcomponent

@CountryScope
@Subcomponent(modules = [CountryModule::class, ViewModelModule::class])
interface CountryComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): CountryComponent
    }

    fun injectCountryActivity(activity: CountryActivity)
}