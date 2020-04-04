package com.example.coronaapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.example.coronaapp.viewModel.CountryVM
import kotlinx.android.synthetic.main.activity_country.*

class CountryActivity : AppCompatActivity() {

    private val model = CountryVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        val name : String? = intent.extras?.getString(KEY_NAME)
        model.search(name)
        model.countryLD.observe(this, Observer{
            Log.e("ERROR", it.toString())
            tv_activity_country.text = it.country
            tv_activity_deaths.text = it.deaths.toString()
            tv_activity_today.text = it.todayDeaths.toString()
            tv_activity_recover.text = it.recovered.toString()
            tv_activity_cases.text = it.cases.toString()
        })
    }

    companion object {
        private const val KEY_NAME = "name"
        fun createIntent(
            activity: FragmentActivity?,
            name: String
        ) =
            Intent(activity, CountryActivity::class.java).apply {
                putExtra(KEY_NAME, name)
            }
    }
}
