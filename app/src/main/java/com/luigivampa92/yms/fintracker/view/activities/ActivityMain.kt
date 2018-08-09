package com.luigivampa92.yms.fintracker.view.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.interfaces.IChangeFragmentInterface
import com.luigivampa92.yms.fintracker.utils.fetchCurrencies
import com.luigivampa92.yms.fintracker.utils.fetchCurrenciesFromAssets
import com.luigivampa92.yms.fintracker.view.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.launch

class ActivityMain : AppCompatActivity(), IChangeFragmentInterface {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchCurrencies(application)
        getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE).edit().putString(Constants.SECONDARY_CURRENCY, "RUB").apply()
        if (savedInstanceState == null) {
            loadFragmentWithoutBackStack(FragmentBalance())
        }

    }

    override fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fragment_open, R.anim.fragment_close, R.anim.fragment_pop_open, R.anim.fragment_pop_close)
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
    }

    override fun loadFragmentWithoutBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fragment_open, R.anim.fragment_close, R.anim.fragment_pop_open, R.anim.fragment_pop_close)
                .replace(R.id.container, fragment)
                .commit()
    }
}
