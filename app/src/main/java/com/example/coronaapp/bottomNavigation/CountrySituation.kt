package com.example.coronaapp.bottomNavigation

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaapp.CountryActivity
import com.example.coronaapp.R
import com.example.coronaapp.corona.CountriesItem
import com.example.coronaapp.di.AppInjector
import com.example.coronaapp.recycler.CountryAdapter
import com.example.coronaapp.viewModel.CountryAllVM
import com.example.coronaapp.di.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.country_fragment.*
import java.io.IOException
import javax.inject.Inject

class CountrySituation : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var model: CountryAllVM
    private var adapter: CountryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        AppInjector.initCountryAllComponent().injectCountryFragment(this)
        initViewModel()
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.country_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list: ArrayList<CountriesItem> = ArrayList()
        adapter = CountryAdapter(list){country ->
            navigateToSecondActivity(country)
        }
        rv_countries.adapter = adapter

        setRecyclerViewItemTouchListener()
    }

    private fun navigateToSecondActivity(country: CountriesItem) {
        startActivity(CountryActivity.createIntent(activity, country.country))
    }

    private fun setRecyclerViewItemTouchListener() {
        val itemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        rv_countries.addItemDecoration(itemTouchHelper)
        itemTouchHelper.attachToRecyclerView(rv_countries)
        getCountries()
    }

    fun getCountries() {
        model.all()
        model.countriesLD.observe(viewLifecycleOwner, Observer{
            adapter?.countryList = it
        })

        adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                queryTextSubmit(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun queryTextSubmit(query: String): Boolean {
        model.search(query)
        model.countryLD.observe(this, Observer{
            try {
                startActivity(CountryActivity.createIntent(activity, it.country))
            } catch (e: IOException) {
                Snackbar.make(
                    requireView().findViewById(android.R.id.content),
                    "City wasn't found",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
        return false;
    }

    fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(CountryAllVM::class.java)
        }
        this.model = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearCountryAllComponent()
    }

    companion object {
        fun newInstance() = CountrySituation()
    }
}
