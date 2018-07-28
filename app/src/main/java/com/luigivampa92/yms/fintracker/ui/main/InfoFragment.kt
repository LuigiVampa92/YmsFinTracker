package com.luigivampa92.yms.fintracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.ContactRouter
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.ui.base.NavigationDrawerFragment
import javax.inject.Inject

class InfoFragment : NavigationDrawerFragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    override fun layoutRes() = R.layout.fragment_info
    override fun navigationItemRes() = R.id.navigation_item_info

    private lateinit var unbinder: Unbinder

    @Inject
    protected lateinit var contactRouter: ContactRouter

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
        contactRouter.openSendEmailPage(Constants.contactEmail)
    }

    @OnClick(R.id.button_contact_vk)
    protected fun buttonContactVkClicked() {
        contactRouter.openVkContactPage(Constants.contactVk)
    }

    @OnClick(R.id.button_contact_tg)
    protected fun buttonContactTgClicked() {
        contactRouter.openTgContactPage(Constants.contactTg)
    }
}