package com.luigivampa92.yms.fintracker.routing.base

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.luigivampa92.yms.fintracker.ContactRouter
import com.luigivampa92.yms.fintracker.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.SystemMessage
import javax.inject.Inject

abstract class BaseRouting<out T: AppCompatActivity> (
        protected val activity: T,
        private val navigatorHolder: NavigatorHolder
) : BindableRouting {

    @Inject
    protected lateinit var contactRouter: ContactRouter

    private val fragmentManager: FragmentManager by lazy { activity.supportFragmentManager }
    protected val currentFragment: Fragment?
        get() = fragmentManager.fragments.firstOrNull()

    private val navigator = Navigator {
        recognizeCommand(it)
    }

    override fun bind() {
        navigatorHolder.setNavigator(navigator)
    }

    override fun unbind() {
        navigatorHolder.removeNavigator()
    }

    protected open fun recognizeCommand(command: Command) {
        when (command) {
            is Forward -> {
                when (command.screenKey) {
                    Screens.EMAIL -> {
                        val email = command.transitionData as String
                        contactRouter.openSendEmailPage(email)
                    }
                    Screens.VK -> {
                        val url = command.transitionData as String
                        contactRouter.openVkContactPage(url)
                    }
                    Screens.TG -> {
                        val url = command.transitionData as String
                        contactRouter.openTgContactPage(url)
                    }
                }
            }
            is Back -> {
                if (!popBack()) {
                    activity.finish()
                }
            }
            is SystemMessage -> {
                Toast.makeText(activity, command.message.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    protected fun openActivity(intent: Intent) {
        activity.startActivity(intent)
    }

    protected fun openFragment(fragment: Fragment, withBackStack:Boolean = false) {
        if (withBackStack) {
            fragmentManager.beginTransaction()
                    .addToBackStack("BACK_STACK")
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }
        else {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, fragment)
                    .commit()
        }
    }

    protected fun replaceFragment(fragment: Fragment) {
        fragmentManager.popBackStackImmediate()
        openFragment(fragment)
    }

    protected fun popBack(): Boolean {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
            return true
        } else {
            return false
        }
    }
}