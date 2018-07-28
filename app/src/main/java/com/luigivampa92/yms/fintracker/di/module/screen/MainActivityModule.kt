package com.luigivampa92.yms.fintracker.di.module.screen

import com.luigivampa92.yms.fintracker.di.scope.FragmentScope
import com.luigivampa92.yms.fintracker.ui.main.BalanceFragment
import com.luigivampa92.yms.fintracker.ui.main.InfoFragment
import com.luigivampa92.yms.fintracker.ui.main.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

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