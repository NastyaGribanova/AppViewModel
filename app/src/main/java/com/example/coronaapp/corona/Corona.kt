package com.example.coronaapp.corona
import com.google.gson.annotations.SerializedName

data class Corona(
    @SerializedName("active")
    var active: Int,
    @SerializedName("affectedCountries")
    var affectedCountries: Int,
    @SerializedName("cases")
    var cases: Int,
    @SerializedName("deaths")
    var deaths: Int,
    @SerializedName("recovered")
    var recovered: Int,
    @SerializedName("updated")
    var updated: Long
)
