package com.luigivampa92.yms.fintracker.ui

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
import com.luigivampa92.yms.fintracker.R
import com.luigivampa92.yms.fintracker.view.activities.ActivityMain
import org.junit.Rule
import org.junit.Test

class ActivityAddRecordUiTest {

    @Test
    fun testActivityAddRecord() {
        //Если sf не пусты
        onView(withId(R.id.fab_fragment_balance)).perform(click())
        onView(withId(R.id.name_activity_add_record)).perform(typeText("Burger"))
        onView(withId(R.id.category_activity_add_record)).perform(typeText("Food"))
        onView(withId(R.id.amount_activity_add_record)).perform(typeText("160"))
        onView(withId(R.id.currency_activity_add_record)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(matches(withSpinnerText(containsString("RUB"))))))
        onView(withId(R.id.done_activity_add_record)).perform(click())
    }


    @Rule
    @JvmField
    val activityRule = ActivityTestRule(ActivityMain::class.java, true, true)
}