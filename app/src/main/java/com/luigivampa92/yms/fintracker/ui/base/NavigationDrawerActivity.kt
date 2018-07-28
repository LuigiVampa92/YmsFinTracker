package com.luigivampa92.yms.fintracker.ui.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.routing.Screens
import com.luigivampa92.yms.fintracker.ui.main.BalanceFragment
import com.luigivampa92.yms.fintracker.ui.main.InfoFragment
import com.luigivampa92.yms.fintracker.ui.main.SettingsFragment

abstract class NavigationDrawerActivity : NestedFragmentActivity() {

    override fun layoutRes() = R.layout.activity_base_with_drawer

    private lateinit var unbinder: Unbinder
    @BindView(R.id.drawer_layout)
    lateinit var drawer: DrawerLayout
    @BindView(R.id.navigation_view)
    lateinit var navigationView: NavigationView

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        unbinder = ButterKnife.bind(this)
        navigationView.setNavigationItemSelectedListener({
            val topFragment = supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1]
            drawer.closeDrawers()
            when (it.itemId) {
                R.id.navigation_item_balance -> {
                    if (topFragment !is BalanceFragment) {
                        router.navigateTo(Screens.BALANCE)
                    }
                }
                R.id.navigation_item_settings -> {
                    if (topFragment !is SettingsFragment) {
                        router.navigateTo(Screens.SETTINGS)
                    }
                }
                R.id.navigation_item_info -> {
                    if (topFragment !is InfoFragment) {
                        router.navigateTo(Screens.INFO)
                    }
                }
            }
            true
        })
    }

    override fun onDestroy() {
        unbinder.unbind()
        super.onDestroy()
    }

    fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.description_navigation_drawer_open, R.string.description_navigation_drawer_close)
        drawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    fun openDrawer() {
        drawer.openDrawer(GravityCompat.START)
    }

    fun selectDrawerItem(@IdRes id: Int) {
        navigationView.setCheckedItem(id)
    }
}