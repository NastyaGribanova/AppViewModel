package com.example.coronaapp.corona

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CoronaService {

    @GET("/countries/{countryName}")
    fun countryByName(@Path("countryName") country: String?): Observable<CountriesItem?>

    @GET("/all")
    fun getAll(): Observable<Corona?>

    @GET("/countries")
    fun getCountry(): Observable<Country>
}
