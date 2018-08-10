package com.luigivampa92.yms.fintracker.interfaces

import android.support.v4.app.Fragment

interface IChangeFragmentInterface{

    fun loadFragment(fragment: Fragment)

    fun loadFragmentWithoutBackStack(fragment: Fragment)

    fun loadFragmentToContainer(fragment: Fragment, containerId: Int)

    fun loadFragmentToContainerWithoutBackStack(fragment: Fragment, containerId: Int)
}