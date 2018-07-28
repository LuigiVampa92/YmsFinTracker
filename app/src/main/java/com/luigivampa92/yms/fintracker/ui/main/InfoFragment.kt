package com.luigivampa92.yms.fintracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.domain.InfoPresenter
import com.luigivampa92.yms.fintracker.ui.base.NavigationDrawerFragment
import javax.inject.Inject

class InfoFragment : NavigationDrawerFragment(), InfoView {

    companion object {
        fun newInstance() = InfoFragment()
    }

    override fun layoutRes() = R.layout.fragment_info
    override fun navigationItemRes() = R.id.navigation_item_info

    @Inject
    @InjectPresenter
    lateinit var presenter: InfoPresenter
    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var unbinder: Unbinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            super.onCreateView(inflater, container, savedInstanceState).also {
                unbinder = ButterKnife.bind(this, it)
            }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }

    @OnClick(R.id.button_contact_email)
    protected fun buttonContactEmailClicked() {
        presenter.openEmail()
    }

    @OnClick(R.id.button_contact_vk)
    protected fun buttonContactVkClicked() {
        presenter.openVk()
    }

    @OnClick(R.id.button_contact_tg)
    protected fun buttonContactTgClicked() {
        presenter.openTg()
    }
}