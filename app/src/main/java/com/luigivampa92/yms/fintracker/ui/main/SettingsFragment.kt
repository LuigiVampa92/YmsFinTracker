package com.luigivampa92.yms.fintracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.ui.base.NavigationDrawerFragment

class SettingsFragment : NavigationDrawerFragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun layoutRes() = R.layout.fragment_settings
    override fun navigationItemRes() = R.id.navigation_item_settings

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            super.onCreateView(inflater, container, savedInstanceState)
}