package com.luigivampa92.yms.fintracker.ui

import android.app.Instrumentation
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.Swipe
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.*
import android.support.test.rule.ActivityTestRule
import android.view.Gravity
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.NavigationViewActions
import com.luigivampa92.yms.fintracker.Constants
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.view.activities.ActivityMain
import org.junit.Rule
import org.junit.Test

class ActivityAddRecordUiTest {

    @Test
    fun testActivityAddRecord() {

        val context = InstrumentationRegistry.getTargetContext()
        val sf = context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        sf.edit().putString(Constants.CURRENT_WALLET_ID, Constants.CURRENT_WALLET_ID).apply()
        //Если sf не пусты
        onView(withId(R.id.fab_fragment_balance)).perform(click())
        onView(withId(R.id.name_activity_add_record)).perform(typeText("Burger"))
        onView(withId(R.id.amount_activity_add_record)).perform(typeText("160"), closeSoftKeyboard())
        onView(withId(R.id.done_activity_add_record)).perform(click())
    }


    @Rule
    @JvmField
    val activityRule = ActivityTestRule(ActivityMain::class.java, true, true)
}