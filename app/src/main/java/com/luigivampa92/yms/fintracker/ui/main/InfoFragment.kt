package com.luigivampa92.yms.fintracker.ui.main

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.ContactRouter
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.ui.base.BaseFragment
import com.luigivampa92.yms.fintracker.ui.base.NavigationDrawerActivity
import javax.inject.Inject

class InfoFragment : BaseFragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    @BindView(R.id.include_toolbar)
    protected lateinit var toolbar: Toolbar

    private lateinit var unbinder: Unbinder
    private lateinit var hostActivity: NavigationDrawerActivity

    @Inject
    protected lateinit var contactRouter: ContactRouter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_info, container, false).also {
                unbinder = ButterKnife.bind(this, it)
                hostActivity = activity as NavigationDrawerActivity
                hostActivity.setToolbar(toolbar)
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