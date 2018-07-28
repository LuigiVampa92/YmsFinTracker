package com.luigivampa92.yms.fintracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.domain.BalancePresenter
import com.luigivampa92.yms.fintracker.ui.base.NavigationDrawerFragment
import javax.inject.Inject

class BalanceFragment : NavigationDrawerFragment(), BalanceView {

    companion object {
        fun newInstance() = BalanceFragment()
    }

    override fun layoutRes() = R.layout.fragment_balance
    override fun navigationItemRes() = R.id.navigation_item_balance

    @Inject
    @InjectPresenter
    lateinit var presenter: BalancePresenter
    @ProvidePresenter
    fun providePresenter() = presenter

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

    override fun onResume() {
        super.onResume()
        presenter.provideCurrentBalance()
    }

    override fun showBalanceUsd(value: String) {
        textBalanceUsd.text = value
    }

    override fun showBalanceRur(value: String) {
        textBalanceRur.text = value
    }
}