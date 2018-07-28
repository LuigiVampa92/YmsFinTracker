package com.luigivampa92.yms.fintracker.ui.main

import com.luigivampa92.yms.fintracker.ui.base.NavigationDrawerActivity

class MainActivity : NavigationDrawerActivity() {

    override fun createFragment() = BalanceFragment.newInstance()
}
