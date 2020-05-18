package com.example.coronaapp.bottomNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coronaapp.R
import com.example.coronaapp.viewModel.WorldVM
import com.example.coronaapp.databinding.WorldFragmentBinding
import com.example.coronaapp.di.AppInjector
import com.example.coronaapp.di.ViewModelFactory
import javax.inject.Inject

class WorldSituation : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var model: WorldVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        AppInjector.initWorldComponent().injectWorldFragment(this)
        initViewModel()
        val binding: WorldFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.world_fragment, container, false)
        model.search()
        model.coronaLD.observe(viewLifecycleOwner, Observer{
            binding.characterWorld = model
        })
        return binding.root
    }

    fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(WorldVM::class.java)
        }
        this.model = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearWorldComponent()
    }

    companion object {
        fun newInstance() = WorldSituation()
    }
}
