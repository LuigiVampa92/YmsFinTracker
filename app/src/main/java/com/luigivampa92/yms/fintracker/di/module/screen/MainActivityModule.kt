package com.luigivampa92.yms.fintracker.di.module.screen

import android.app.Activity
import com.luigivampa92.yms.fintracker.di.scope.ActivityScope
import com.luigivampa92.yms.fintracker.di.scope.FragmentScope
import com.luigivampa92.yms.fintracker.routing.base.BindableRouting
import com.luigivampa92.yms.fintracker.routing.MainScreenRouting
import com.luigivampa92.yms.fintracker.ui.main.*
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ActivityScope
    @Binds
    abstract fun activity(activity: MainActivity): Activity

    @ActivityScope
    @Binds
    abstract fun routing(routing: MainScreenRouting): BindableRouting

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun balanceFragmentInjector(): BalanceFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun SettingsFragmentInjector(): SettingsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun infoFragmentInjector(): InfoFragment
}