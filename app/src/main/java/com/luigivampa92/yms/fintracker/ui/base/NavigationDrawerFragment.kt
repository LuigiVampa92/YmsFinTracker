package com.luigivampa92.yms.fintracker.ui.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup
import com.luigivampa92.yms.fintracker.R

abstract class NavigationDrawerFragment : BaseFragment() {

    protected lateinit var hostActivity: NavigationDrawerActivity

    @LayoutRes
    abstract fun layoutRes(): Int

    @IdRes
    abstract fun navigationItemRes(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(layoutRes(), container, false).also {
                hostActivity = activity as NavigationDrawerActivity
                hostActivity.setToolbar(it.findViewById(R.id.include_toolbar))
            }

    override fun onResume() {
        super.onResume()
        hostActivity.selectDrawerItem(navigationItemRes())
    }
}