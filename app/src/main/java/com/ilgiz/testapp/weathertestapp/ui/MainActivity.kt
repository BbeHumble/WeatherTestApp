package com.ilgiz.testapp.weathertestapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ilgiz.testapp.weathertestapp.R
import com.ilgiz.testapp.weathertestapp.ui.weatherScreen.WeatherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.ac_container) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mFragment: Fragment?
        mFragment = WeatherFragment()
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.container, mFragment).commit()
    }
}
