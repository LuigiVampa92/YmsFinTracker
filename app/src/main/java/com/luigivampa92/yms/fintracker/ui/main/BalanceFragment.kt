package com.luigivampa92.yms.fintracker.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.arellomobile.mvp.presenter.InjectPresenter
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.domain.BalancePresenter
import com.luigivampa92.yms.fintracker.ui.account.AccountActivity
import com.luigivampa92.yms.fintracker.ui.addrecord.AddRecordActivity
import com.luigivampa92.yms.fintracker.ui.base.NavigationDrawerFragment

class BalanceFragment : NavigationDrawerFragment(), BalanceView {

    companion object {
        fun newInstance() = BalanceFragment()
    }

    override fun layoutRes() = R.layout.fragment_balance
    override fun navigationItemRes() = R.id.navigation_item_balance

    @InjectPresenter
    lateinit var presenter: BalancePresenter

    private lateinit var unbinder: Unbinder
    @BindView(R.id.text_balance_usd)
    protected lateinit var textBalanceUsd: TextView
    @BindView(R.id.text_balance_rur)
    protected lateinit var textBalanceRur: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            super.onCreateView(inflater, container, savedInstanceState).also {
                unbinder = ButterKnife.bind(this, it)
            }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }

    override fun showBalanceUsd(value: String) {
        textBalanceUsd.text = value
    }

    override fun showBalanceRur(value: String) {
        textBalanceRur.text = value
    }

    @OnClick(R.id.button_add)
    protected fun buttonAddClicked() {
        activity!!.startActivity(Intent(context!!, AddRecordActivity::class.java))
    }

    @OnClick(R.id.button_account)
    protected fun buttonAccounClicked() {
        activity!!.startActivity(Intent(context!!, AccountActivity::class.java))
    }
}