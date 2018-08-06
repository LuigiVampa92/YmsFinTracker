package com.luigivampa92.yms.fintracker.view.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.model.Currency
import com.luigivampa92.yms.fintracker.utils.createId
import com.luigivampa92.yms.fintracker.utils.fetchCurrencies
import com.luigivampa92.yms.fintracker.utils.loadJsonFromAssets
import com.luigivampa92.yms.fintracker.view.fragments.FragmentBalance
import com.luigivampa92.yms.fintracker.view.fragments.FragmentInfo
import com.luigivampa92.yms.fintracker.view.fragments.FragmentSettings
import com.luigivampa92.yms.fintracker.view.fragments.FragmentWallets
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.launch

class ActivityMain : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        // на всякий случай, пока не привязал валюты из бд, гружу из json
        launch {
            loadJsonFromAssets(context)
        }
        fetchCurrencies(application)
        //Потом будет использоваться
        getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE).edit().putString(Constants.SECONDARY_CURRENCY, "RUB").apply()
        if (savedInstanceState == null) {
            loadFragmentWithoutBackStack(FragmentBalance())
        }

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents() {
        toolbar.setNavigationIcon(R.drawable.ic_dehaze)
        toolbar.title = resources.getString(R.string.finance_tracker)
        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.description_navigation_drawer_open, R.string.description_navigation_drawer_close)
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun initComponentsListeners() {

        toolbar.setNavigationOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }

        navigation_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_item_balance -> {
                    loadFragment(FragmentBalance())
                }
                R.id.navigation_item_settings -> {
                    loadFragment(FragmentSettings())
                }
                R.id.navigation_item_info -> {
                    loadFragment(FragmentInfo())
                }
                else -> {
                    loadFragment(FragmentWallets())
                }
            }
            it.isChecked = true
            drawer_layout.closeDrawers()
            true
        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
    }

    private fun loadFragmentWithoutBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}
