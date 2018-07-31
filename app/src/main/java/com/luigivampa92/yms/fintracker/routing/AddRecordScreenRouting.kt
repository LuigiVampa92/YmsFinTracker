package com.luigivampa92.yms.fintracker.routing

import com.luigivampa92.yms.fintracker.routing.base.BaseRouting
import com.luigivampa92.yms.fintracker.ui.addrecord.AddRecordActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class AddRecordScreenRouting @Inject constructor (
        activity: AddRecordActivity,
        navigatorHolder: NavigatorHolder
) : BaseRouting<AddRecordActivity>(activity, navigatorHolder) {

    override fun recognizeCommand(command: Command) {
        super.recognizeCommand(command)
    }
}