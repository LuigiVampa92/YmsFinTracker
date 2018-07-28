package com.luigivampa92.yms.fintracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.ui.base.BaseFragment
import com.luigivampa92.yms.fintracker.ui.base.NavigationDrawerActivity

class SettingsFragment : BaseFragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var unbinder: Unbinder
    private lateinit var hostActivity: NavigationDrawerActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_settings, container, false).also {
                unbinder = ButterKnife.bind(this, it)
                hostActivity = activity as NavigationDrawerActivity
            }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }

    @OnClick(R.id.button_navigation_menu)
    protected fun buttonMenuClicked() {
        hostActivity.openDrawer()
    }
}