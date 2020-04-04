package com.example.coronaapp.corona
import com.google.gson.annotations.SerializedName

class Country : ArrayList<CountriesItem>()

data class CountriesItem(
    @SerializedName("active")
    var active: Int,
    @SerializedName("cases")
    var cases: Int,
    @SerializedName("casesPerOneMillion")
    var casesPerOneMillion: Double,
    @SerializedName("country")
    var country: String,
    @SerializedName("countryInfo")
    var countryInfo: CountryInfo,
    @SerializedName("critical")
    var critical: Int,
    @SerializedName("deaths")
    var deaths: Int,
    @SerializedName("deathsPerOneMillion")
    var deathsPerOneMillion: Double,
    @SerializedName("recovered")
    var recovered: Int,
    @SerializedName("todayCases")
    var todayCases: Int,
    @SerializedName("todayDeaths")
    var todayDeaths: Int,
    @SerializedName("updated")
    var updated: Long
)

data class CountryInfo(
    @SerializedName("flag")
    var flag: String,
    @SerializedName("_id")
    var id: Int,
    @SerializedName("iso2")
    var iso2: String,
    @SerializedName("iso3")
    var iso3: String,
    @SerializedName("lat")
    var lat: Double,
    @SerializedName("long")
    var long: Double
)
