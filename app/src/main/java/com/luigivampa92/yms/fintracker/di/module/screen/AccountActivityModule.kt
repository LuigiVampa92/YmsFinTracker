package com.luigivampa92.yms.fintracker.di.module.screen

import android.app.Activity
import com.luigivampa92.yms.fintracker.di.scope.ActivityScope
import com.luigivampa92.yms.fintracker.di.scope.FragmentScope
import com.luigivampa92.yms.fintracker.routing.AccountScreenRouting
import com.luigivampa92.yms.fintracker.routing.base.BindableRouting
import com.luigivampa92.yms.fintracker.ui.account.AccountActivity
import com.luigivampa92.yms.fintracker.ui.account.AccountFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AccountActivityModule {

    @ActivityScope
    @Binds
    abstract fun activity(activity: AccountActivity): Activity

    @ActivityScope
    @Binds
    abstract fun routing(routing: AccountScreenRouting): BindableRouting

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun accountFragmentInjector(): AccountFragment
}