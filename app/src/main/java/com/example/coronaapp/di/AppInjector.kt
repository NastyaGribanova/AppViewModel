package com.example.coronaapp.di

import com.example.coronaapp.App
import com.example.coronaapp.CountryActivity
import com.example.coronaapp.bottomNavigation.CountrySituation
import com.example.coronaapp.bottomNavigation.WorldSituation
import com.example.coronaapp.di.component.*

object AppInjector {

    lateinit var appComponent: AppComponent
    private var countryComponent: CountryComponent? = null
    private var countryAllComponent: CountryAllComponent? = null
    private var worldComponent: WorldComponent? = null

    fun init(application: App) {
        appComponent = DaggerAppComponent.builder()
            .application(application)
            .build()
    }

    fun initCountryComponent(): CountryComponent = countryComponent?:
    appComponent.plusCountryComponentBuilder().build()
            .also {
                countryComponent = it
            }

    fun initCountryAllComponent(): CountryAllComponent = countryAllComponent?:
    appComponent.plusCountryAllComponentBuilder().build()
            .also {
                countryAllComponent = it
            }

    fun initWorldComponent(): WorldComponent = worldComponent?:
    appComponent.plusWorldComponentBuilder().build()
            .also {
                worldComponent = it
            }

    fun clearCountryComponent() {
        countryComponent = null
    }

    fun clearCountryAllComponent() {
        countryAllComponent = null
    }

    fun clearWorldComponent() {
        worldComponent = null
    }
}