package com.example.coronaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.coronaapp.bottomNavigation.CountrySituation
import com.example.coronaapp.bottomNavigation.Tips
import com.example.coronaapp.bottomNavigation.WorldSituation
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView

        val onNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener {item ->
                when (item.itemId) {
                    R.id.world -> {
                        val doctorFragment = WorldSituation.newInstance()
                        openFragment(doctorFragment)
                        true
                    }
                    R.id.tips -> {
                        val companionFragment = Tips.newInstance()
                        openFragment(companionFragment)
                        true
                    }
                    R.id.country -> {
                        val doctorWhoFragment = CountrySituation.newInstance()
                        openFragment(doctorWhoFragment)
                        true
                    }
                    else -> false
                }
            }
        btv_main.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun openFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction().apply { }
        transaction.replace(R.id.container_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
