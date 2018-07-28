package com.luigivampa92.yms.fintracker.domain

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.di.scope.FragmentScope
import com.luigivampa92.yms.fintracker.routing.Screens
import com.luigivampa92.yms.fintracker.ui.main.InfoView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@FragmentScope
@InjectViewState
class InfoPresenter @Inject constructor(
        private val router: Router
) : MvpPresenter<InfoView>() {

    fun openEmail() {
        router.navigateTo(Screens.EMAIL, Constants.contactEmail)
    }

    fun openVk() {
        router.navigateTo(Screens.VK, Constants.contactVk)
    }

    fun openTg() {
        router.navigateTo(Screens.TG, Constants.contactTg)
    }
}