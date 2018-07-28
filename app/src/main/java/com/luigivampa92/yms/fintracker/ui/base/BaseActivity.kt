package com.luigivampa92.yms.fintracker.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.luigivampa92.yms.fintracker.R
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    protected lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    fun openFragment(fragment: Fragment, withBackStack:Boolean = false) {
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