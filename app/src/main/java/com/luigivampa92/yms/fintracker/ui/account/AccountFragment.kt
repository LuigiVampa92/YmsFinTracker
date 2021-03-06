package com.luigivampa92.yms.fintracker.ui.account

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.ui.base.BaseFragment

class AccountFragment : BaseFragment() {

    companion object {
        fun newInstance(): AccountFragment {
            val fragment = AccountFragment()
            fragment.setHasOptionsMenu(true)
            return fragment
        }
    }

    private lateinit var unbinder: Unbinder
    @BindView(R.id.include_toolbar)
    protected lateinit var toolbar: Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_account, container, false).also {
                unbinder = ButterKnife.bind(this, it)
                setupToolbar()
            }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            activity?.finish()
            return true
        }
        else return super.onOptionsItemSelected(item)
    }


    private fun setupToolbar() {
        val addRecordActivity = activity as AccountActivity
        addRecordActivity.setSupportActionBar(toolbar)
        addRecordActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
        addRecordActivity.supportActionBar?.setHomeButtonEnabled(true)
        addRecordActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}