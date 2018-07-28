package com.luigivampa92.yms.fintracker.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import com.luigivampa92.yms.fintracker.R

abstract class NestedFragmentActivity : BaseActivity() {

    protected abstract fun createFragment(): Fragment

    @LayoutRes
    open fun layoutRes() = R.layout.activity_base

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())

        val fragmentManager = supportFragmentManager
        val fragment: Fragment? = fragmentManager.findFragmentById(R.id.fragment_container)
        if(fragment == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, createFragment())
                    .commit()
        }
    }
}