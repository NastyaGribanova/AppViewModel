package com.example.coronaapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coronaapp.corona.ApiFactory
import com.example.coronaapp.corona.CoronaService
import com.example.coronaapp.corona.CountriesItem
import com.example.coronaapp.corona.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountryVM: ViewModel() {

    @Inject
    lateinit var service: CoronaService

    val country: MutableLiveData<CountriesItem> by lazy { MutableLiveData<CountriesItem>() }
    val countryLD: LiveData<CountriesItem> = country

    fun search(name: String?) {
        var result = service.countryByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                country.value = result
            })
    }
}
