package com.example.coronaapp.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaapp.R
import com.example.coronaapp.corona.CountriesItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_country.*

class CountryHolder (
    override val containerView: View,
    private val clickLambda: (CountriesItem) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(country: CountriesItem) {
        tv_country.text = country.country
        tv_deaths.text = country.deaths.toString()

        itemView.setOnClickListener {
            clickLambda(country)
        }
    }

    companion object {
        fun create(parent: ViewGroup, clickLambda: (CountriesItem) -> Unit) =
            CountryHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false),
                clickLambda
            )
    }
}
