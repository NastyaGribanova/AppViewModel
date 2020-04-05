package com.example.coronaapp.bottomNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.coronaapp.R
import com.example.coronaapp.viewModel.WorldVM
import kotlinx.android.synthetic.main.activity_main.*
import com.example.coronaapp.databinding.WorldFragmentBinding

class WorldSituation : Fragment() {

    private val model = WorldVM()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val binding: WorldFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.world_fragment, container, false)
        model.search()
        model.coronaLD.observe(viewLifecycleOwner, Observer{
            binding.characterWorld = model
        })
        return binding.root
    }

    companion object {
        fun newInstance() = WorldSituation()
    }
}
