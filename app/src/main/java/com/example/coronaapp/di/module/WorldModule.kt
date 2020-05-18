package com.example.coronaapp.di.module

import androidx.lifecycle.ViewModel
import com.example.coronaapp.di.ViewModelKey
import com.example.coronaapp.di.scope.WorldScope
import com.example.coronaapp.viewModel.WorldVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorldModule {

    @Binds
    @IntoMap
    @WorldScope
    @ViewModelKey(WorldVM::class)
    fun bindWorldViewModel(viewModel: WorldVM): ViewModel
}