package com.luigivampa92.yms.fintracker.di.module.screen

import android.app.Activity
import com.luigivampa92.yms.fintracker.di.scope.ActivityScope
import com.luigivampa92.yms.fintracker.di.scope.FragmentScope
import com.luigivampa92.yms.fintracker.routing.AddRecordScreenRouting
import com.luigivampa92.yms.fintracker.routing.base.BindableRouting
import com.luigivampa92.yms.fintracker.ui.addrecord.AddRecordActivity
import com.luigivampa92.yms.fintracker.ui.addrecord.AddRecordFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddRecordActivityModule {

    @ActivityScope
    @Binds
    abstract fun activity(activity: AddRecordActivity): Activity

    @ActivityScope
    @Binds
    abstract fun routing(routing: AddRecordScreenRouting): BindableRouting

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun addRecordFragmentInjector(): AddRecordFragment
}