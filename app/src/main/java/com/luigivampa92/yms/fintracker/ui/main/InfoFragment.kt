package com.luigivampa92.yms.fintracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.ui.base.NavigationDrawerFragment

class InfoFragment : NavigationDrawerFragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    override fun layoutRes() = R.layout.fragment_info
    override fun navigationItemRes() = R.id.navigation_item_info

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            super.onCreateView(inflater, container, savedInstanceState)
}