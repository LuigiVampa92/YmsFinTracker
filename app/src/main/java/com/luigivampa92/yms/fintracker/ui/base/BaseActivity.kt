package com.luigivampa92.yms.fintracker.ui.base

import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.luigivampa92.yms.fintracker.R

abstract class BaseActivity : MvpAppCompatActivity() {

    protected fun openFragment(fragment: Fragment, withBackStack:Boolean = false) {
        if (withBackStack) {
            supportFragmentManager.beginTransaction()
                    .addToBackStack("BACK_STACK")
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }
        else {
            supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, fragment)
                    .commit()
        }
    }
}