package com.luigivampa92.yms.fintracker.ui

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.Swipe
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.*
import android.support.test.rule.ActivityTestRule
import android.view.Gravity
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.DrawerMatchers.isOpen
import android.support.test.espresso.contrib.NavigationViewActions
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.view.activities.ActivityMain
import org.junit.Rule
import org.junit.Test

class ActivityAddWalletTest {

//    @Test
//    fun testAddWalletActivity() {
//        //Если sf не пусты
//        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
//        onView(withId(R.id.drawer_layout)).check(matches(isOpen()))
//        onView(withId(R.id.navigation_view)).perform(NavigationViewActions.navigateTo(R.id.navigation_item_wallets));
//        onView(withId(R.id.drawer_layout)).perform(DrawerActions.close())
//        onView(withId(R.id.fab_fragment_wallets)).perform(ViewActions.click())
//        onView(withId(R.id.name_activity_add_wallet)).perform(ViewActions.click())
//        onView(withId(R.id.name_activity_add_wallet)).perform(ViewActions.typeText("Wallet 2"))
//        onView(withId(R.id.balance_activity_add_wallet)).perform(ViewActions.typeText("120"))
//        onView(withId(R.id.currency_activity_add_wallet)).perform(ViewActions.click())
//        onData(allOf(`is`(instanceOf(String::class.java)),`is`(matches(withSpinnerText(containsString("RUB"))))))
//    }

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(ActivityMain::class.java, true, true)
}