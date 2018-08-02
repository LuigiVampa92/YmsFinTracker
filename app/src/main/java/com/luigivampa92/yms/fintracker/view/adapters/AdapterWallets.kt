package com.luigivampa92.yms.fintracker.view.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.luigivampa92.yms.fintracker.view.fragments.FragmentWallet

class AdapterWallets(fm: FragmentManager): FragmentStatePagerAdapter(fm){

    private val mFragments = mutableListOf<FragmentWallet>()

    override fun getItem(p0: Int): Fragment {
        return mFragments[p0]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    fun addFragment(fragment: FragmentWallet){
        mFragments.add(fragment)
    }
}