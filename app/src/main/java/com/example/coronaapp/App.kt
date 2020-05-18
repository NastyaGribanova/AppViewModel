package com.example.coronaapp

import android.app.Application
import com.example.coronaapp.di.AppInjector
import com.example.coronaapp.di.component.AppComponent
import com.example.coronaapp.di.component.NetComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var netComponent: NetComponent
    }
}