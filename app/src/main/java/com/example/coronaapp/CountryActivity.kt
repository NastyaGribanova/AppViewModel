package com.example.coronaapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coronaapp.di.AppInjector
import com.example.coronaapp.viewModel.CountryVM
import kotlinx.android.synthetic.main.activity_country.*
import javax.inject.Inject

class CountryActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var model: CountryVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.initCountryComponent().injectCountryActivity(this)
        initViewModel()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)


        val name : String? = intent.extras?.getString(KEY_NAME)
        model?.search(name)
        model?.countryLD?.observe(this, Observer{
            Log.e("ERROR", it.toString())
            tv_activity_country.text = it.country
            tv_activity_deaths.text = it.deaths.toString()
            tv_activity_today.text = it.todayDeaths.toString()
            tv_activity_recover.text = it.recovered.toString()
            tv_activity_cases.text = it.cases.toString()
        })
    }

    fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(CountryVM::class.java)
        }
        this.model = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearCountryComponent()
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
