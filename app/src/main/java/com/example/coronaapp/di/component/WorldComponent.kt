package com.example.coronaapp.di.component

import com.example.coronaapp.bottomNavigation.WorldSituation
import com.example.coronaapp.di.module.ViewModelModule
import com.example.coronaapp.di.scope.ActivityScope
import com.example.coronaapp.di.module.WorldModule
import com.example.coronaapp.di.scope.WorldScope
import dagger.Subcomponent

@WorldScope
@Subcomponent(modules = [WorldModule::class, ViewModelModule::class])
interface WorldComponent {

    @Subcomponent.Factory
    interface Builder {
        fun build(): WorldComponent
    }

    fun injectWorldFragment(fragment: WorldSituation)
}