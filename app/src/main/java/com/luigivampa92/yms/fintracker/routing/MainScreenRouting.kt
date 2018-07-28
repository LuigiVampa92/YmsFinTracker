package com.luigivampa92.yms.fintracker.routing

import com.luigivampa92.yms.fintracker.ui.main.BalanceFragment
import com.luigivampa92.yms.fintracker.ui.main.InfoFragment
import com.luigivampa92.yms.fintracker.ui.main.MainActivity
import com.luigivampa92.yms.fintracker.ui.main.SettingsFragment
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
                }
            }
        }
    }
}