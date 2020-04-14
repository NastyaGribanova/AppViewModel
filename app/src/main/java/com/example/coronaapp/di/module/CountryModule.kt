package com.example.coronaapp.di.module

import androidx.lifecycle.ViewModel
import com.example.coronaapp.di.ViewModelKey
import com.example.coronaapp.di.scope.CountryScope
import com.example.coronaapp.viewModel.CountryVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CountryModule {

    @Binds
    @IntoMap
    @CountryScope
    @ViewModelKey(CountryVM::class)
    fun bindCountryViewModel(viewModel: CountryVM): ViewModel
}