package com.luigivampa92.yms.fintracker.view.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.view.fragments.FragmentBalance
import com.luigivampa92.yms.fintracker.view.fragments.FragmentInfo
import com.luigivampa92.yms.fintracker.view.fragments.FragmentSettings
import kotlinx.android.synthetic.main.activity_main.*

class ActivityMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            loadFragmentWithoutBackStack(FragmentBalance())
        }

        initComponents()
        initComponentsListeners()
    }

    private fun initComponents() {
        toolbar.setNavigationIcon(R.drawable.ic_dehaze)
        toolbar.title = resources.getString(R.string.finance_tracker)
        toolbar.inflateMenu(R.menu.menu_toolbar)
        val drawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.description_navigation_drawer_open, R.string.description_navigation_drawer_close)
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun initComponentsListeners() {

        toolbar.setNavigationOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }

        toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.action_add_wallet){
                startActivity(Intent(this, ActivityWallets::class.java))
            }
             true
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
                    loadFragment(FragmentBalance())
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