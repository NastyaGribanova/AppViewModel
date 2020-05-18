package com.example.coronaapp.di.component

import com.example.coronaapp.di.scope.NetScope
import com.example.coronaapp.di.module.NetModule
import dagger.Subcomponent
import retrofit2.Retrofit

@Subcomponent(modules = [NetModule::class])
@NetScope
interface NetComponent {

    fun getRetrofit(): Retrofit

    @Subcomponent.Builder
    interface Builder{
        fun netModule(netModule: NetModule): Builder
        fun build(): NetComponent
    }
}