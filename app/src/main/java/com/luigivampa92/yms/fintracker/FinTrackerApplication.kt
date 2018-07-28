package com.luigivampa92.yms.fintracker

import android.app.Activity
import android.app.Application
import com.luigivampa92.yms.fintracker.di.component.AppComponent
import com.luigivampa92.yms.fintracker.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class FinTrackerApplication : Application(), HasActivityInjector {

    companion object {
        @JvmStatic lateinit var INSTANCE: FinTrackerApplication
    }

    @Inject
    protected lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    private lateinit var appComponent: AppComponent
    fun getAppComponent() = appComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        appComponent = DaggerAppComponent.builder()
                .withContext(this)
                .build()
        appComponent.inject(this)
    }
}