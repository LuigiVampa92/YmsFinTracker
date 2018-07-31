package com.luigivampa92.yms.fintracker.routing

import com.luigivampa92.yms.fintracker.routing.base.BaseRouting
import com.luigivampa92.yms.fintracker.ui.account.AccountActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class AccountScreenRouting @Inject constructor (
        activity: AccountActivity,
        navigatorHolder: NavigatorHolder
) : BaseRouting<AccountActivity>(activity, navigatorHolder) {

    override fun recognizeCommand(command: Command) {
        super.recognizeCommand(command)
    }
}