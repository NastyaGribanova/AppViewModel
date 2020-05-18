package com.example.coronaapp.di.module

import androidx.lifecycle.ViewModel
import com.example.coronaapp.di.ViewModelKey
import com.example.coronaapp.di.scope.CountryAllScope
import com.example.coronaapp.viewModel.CountryAllVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CountryAllModule {

    @Binds
    @IntoMap
    @CountryAllScope
    @ViewModelKey(CountryAllVM::class)
    fun bindCountryAllViewModel(viewModel: CountryAllVM): ViewModel
}