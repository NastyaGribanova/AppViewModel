package com.example.coronaapp.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaapp.corona.CountriesItem

class CountryAdapter(
    var countryList: List<CountriesItem>,
    private val clickLambda: (CountriesItem) -> Unit
) : RecyclerView.Adapter<CountryHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder =
        CountryHolder.create(
            parent,
            clickLambda
        )

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: CountryHolder, position: Int) =
        holder.bind(countryList[position])
}
