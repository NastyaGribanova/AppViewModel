package com.example.coronaapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coronaapp.corona.ApiFactory
import com.example.coronaapp.corona.Corona
import com.example.coronaapp.corona.CoronaService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorldVM : ViewModel() {

    @Inject
    lateinit var service: CoronaService

    val corona: MutableLiveData<Corona> by lazy { MutableLiveData<Corona>() }
    val coronaLD: LiveData<Corona> = corona

    private val affCountries = MutableLiveData("String")
    private val cases = MutableLiveData("String")
    private val deaths = MutableLiveData("String")
    private val recover = MutableLiveData("String")

    val affCountriesLD: LiveData<String> = affCountries
    val casesLD: LiveData<String> = cases
    val deathsLD: LiveData<String> = deaths
    val recoverLD: LiveData<String> = recover

    fun search() {
        var result = service.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                corona.value = result
                affCountries.value = corona.value?.affectedCountries.toString()
                cases.value = corona.value?.cases.toString()
                deaths.value = corona.value?.deaths.toString()
                recover.value = corona.value?.recovered.toString()
            })
    }
}
