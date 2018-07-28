package com.luigivampa92.yms.fintracker.ui.main

import android.os.Bundle
import android.support.v7.widget.Toolbar
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
import com.luigivampa92.yms.fintracker.ui.base.BaseFragment
import com.luigivampa92.yms.fintracker.ui.base.NavigationDrawerActivity
import javax.inject.Inject

class BalanceFragment : BaseFragment(), BalanceView {

    companion object {
        fun newInstance() = BalanceFragment()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: BalancePresenter
    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var unbinder: Unbinder
    @BindView(R.id.include_toolbar)
    protected lateinit var toolbar: Toolbar
    @BindView(R.id.text_balance_usd)
    protected lateinit var textBalanceUsd: TextView
    @BindView(R.id.text_balance_rur)
    protected lateinit var textBalanceRur: TextView

    private lateinit var hostActivity: NavigationDrawerActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_balance, container, false).also {
                unbinder = ButterKnife.bind(this, it)
                hostActivity = activity as NavigationDrawerActivity
                hostActivity.setToolbar(toolbar)
            }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        hostActivity.selectDrawerItem(R.id.navigation_item_balance)

        presenter.provideCurrentBalance()
    }

    override fun showBalanceUsd(value: String) {
        textBalanceUsd.text = value
    }

    override fun showBalanceRur(value: String) {
        textBalanceRur.text = value
    }
}