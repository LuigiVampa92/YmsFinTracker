package com.luigivampa92.yms.fintracker.ui.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.ui.main.BalanceFragment
import com.luigivampa92.yms.fintracker.ui.main.InfoFragment
import com.luigivampa92.yms.fintracker.ui.main.SettingsFragment
import kotlinx.android.synthetic.main.activity_base_with_drawer.*

abstract class NavigationDrawerActivity : NestedFragmentActivity() {

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_with_drawer)
        navigation_view.setNavigationItemSelectedListener {
            val topFragment = supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1]
            drawer_layout.closeDrawers()
            when (it.itemId) {
                R.id.navigation_item_balance -> {
                    if (topFragment !is BalanceFragment) {
                        openFragment(BalanceFragment.newInstance())
                    }
                }
                R.id.navigation_item_settings -> {
                    if (topFragment !is SettingsFragment) {
                        openFragment(SettingsFragment.newInstance())
                    }
                }
                R.id.navigation_item_info -> {
                    if (topFragment !is InfoFragment) {
                        openFragment(InfoFragment.newInstance())
                    }
                }
            }
            true
        }
    }


    fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.description_navigation_drawer_open, R.string.description_navigation_drawer_close)
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }


    fun selectDrawerItem(@IdRes id: Int) {
        navigation_view.setCheckedItem(id)
    }
}