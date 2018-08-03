package com.luigivampa92.yms.fintracker.view.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.model.Wallet
import com.luigivampa92.yms.fintracker.view.fragments.FragmentWallet

class AdapterWallets(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var mVisibleItemPosition = -1
    private val mFragments = mutableListOf<FragmentWallet>()
    private val mWallets = mutableListOf<Wallet>()

    override fun getItem(p0: Int): Fragment {
        mVisibleItemPosition = p0
        return mFragments[p0]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    fun addFragment(wallet: Wallet) {
        if (!contains(wallet)) {
            val fragment = FragmentWallet()
            val bundle = Bundle()
            bundle.putString(Constants.NAME, wallet.name)
            bundle.putString(Constants.BALANCE, wallet.balance.toString())
            fragment.arguments = bundle
            mFragments.add(fragment)
        }
    }

    fun getWalletName(): String? {
        if (mVisibleItemPosition >= 0) {
            return mFragments[mVisibleItemPosition].getFragmentName()
        }
        return null
    }

    fun contains(wallet: Wallet): Boolean {
        mWallets.forEach {
            if (it.id == wallet.id) return true
        }
        return false
    }
}