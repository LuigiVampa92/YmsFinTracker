package com.luigivampa92.yms.fintracker.routing

import com.luigivampa92.yms.fintracker.routing.base.BaseRouting
import com.luigivampa92.yms.fintracker.routing.base.Screens
import com.luigivampa92.yms.fintracker.ui.account.AccountActivity
import com.luigivampa92.yms.fintracker.ui.addrecord.AddRecordActivity
import com.luigivampa92.yms.fintracker.ui.main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class MainScreenRouting @Inject constructor (
        activity: MainActivity,
        navigatorHolder: NavigatorHolder
): BaseRouting<MainActivity>(activity, navigatorHolder) {

    override fun recognizeCommand(command: Command) {
        super.recognizeCommand(command)

        when (command) {
            is Forward -> {
                when (command.screenKey) {
                    Screens.BALANCE -> {
                        openFragment(BalanceFragment.newInstance())
                    }
                    Screens.SETTINGS -> {
                        openFragment(SettingsFragment.newInstance())
                    }
                    Screens.INFO -> {
                        openFragment(InfoFragment.newInstance())
                    }
                    Screens.ACCOUNTS -> {
                        openActivity(AccountActivity.newIntent(activity))
                    }
                    Screens.ADD_RECORD -> {
                        openActivity(AddRecordActivity.newIntent(activity))
                    }
                }
            }
        }
    }
}