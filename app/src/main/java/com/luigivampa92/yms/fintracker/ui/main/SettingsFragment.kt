package com.luigivampa92.yms.fintracker.ui.main

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.ui.base.NavigationDrawerFragment

class SettingsFragment : NavigationDrawerFragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun layoutRes() = R.layout.fragment_settings
    override fun navigationItemRes() = R.id.navigation_item_settings

    private lateinit var unbinder: Unbinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            super.onCreateView(inflater, container, savedInstanceState).also {
                unbinder = ButterKnife.bind(this, it)
            }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }
}