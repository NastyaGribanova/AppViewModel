package com.example.coronaapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.coronaapp.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
