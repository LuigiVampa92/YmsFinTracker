package com.luigivampa92.yms.fintracker.view.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.view.fragments.FragmentWallet

class AdapterWallets(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var mVisibleItemPosition = -1
    private val mFragments = mutableListOf<FragmentWallet>()

    override fun getItem(p0: Int): Fragment {
        mVisibleItemPosition = p0
        return mFragments[p0]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    fun addFragment(fragment: FragmentWallet) {
        mFragments.add(fragment)
    }

    fun getWalletName(): String {
        return mFragments[mVisibleItemPosition].getFragmentName()
    }
}