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

        //initComponents()
        //initComponentsListeners()
    }

//    private fun initComponents() {
//        toolbar.setNavigationIcon(R.drawable.ic_dehaze)
//        toolbar.title = resources.getString(R.string.finance_tracker)
//        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.description_navigation_drawer_open, R.string.description_navigation_drawer_close)
//        drawer_layout.addDrawerListener(drawerToggle)
//        drawerToggle.syncState()
//    }

//    private fun initComponentsListeners() {
//
//        toolbar.setNavigationOnClickListener {
//            drawer_layout.openDrawer(GravityCompat.START)
//        }
//
//        navigation_view.setNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.navigation_item_balance -> {
//                    loadFragment(FragmentBalance())
//                }
//                R.id.navigation_item_settings -> {
//                    loadFragment(FragmentSettings())
//                }
//                R.id.navigation_item_info -> {
//                    loadFragment(FragmentInfo())
//                }
//                R.id.navigation_item_templates -> {
//                    loadFragment(FragmentTemplates())
//                }
//                else -> {
//                    loadFragment(FragmentWallets())
//                }
//            }
//            it.isChecked = true
//            drawer_layout.closeDrawers()
//            true
//        }
//
//    }

    override fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
    }

    override fun loadFragmentWithoutBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}
