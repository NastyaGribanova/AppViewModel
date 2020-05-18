package com.example.coronaapp.di.component

import com.example.coronaapp.bottomNavigation.CountrySituation
import com.example.coronaapp.di.module.CountryAllModule
import com.example.coronaapp.di.module.ViewModelModule
import com.example.coronaapp.di.scope.ActivityScope
import com.example.coronaapp.di.scope.CountryAllScope
import dagger.Subcomponent

@CountryAllScope
@Subcomponent(modules = [CountryAllModule::class, ViewModelModule::class])
interface CountryAllComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): CountryAllComponent
    }

    fun injectCountryFragment(fragment: CountrySituation)
}