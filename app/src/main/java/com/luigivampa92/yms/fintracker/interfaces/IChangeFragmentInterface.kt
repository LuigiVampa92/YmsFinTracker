package com.luigivampa92.yms.fintracker.interfaces

import android.support.v4.app.Fragment

interface IChangeFragmentInterface{

    fun loadFragment(fragment: Fragment)

    fun loadFragmentWithoutBackStack(fragment: Fragment)
}