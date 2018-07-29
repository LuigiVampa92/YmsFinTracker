package com.luigivampa92.yms.fintracker.di.module

import com.luigivampa92.yms.fintracker.di.module.screen.AccountActivityModule
import com.luigivampa92.yms.fintracker.di.module.screen.AddRecordActivityModule
import com.luigivampa92.yms.fintracker.di.module.screen.MainActivityModule
import com.luigivampa92.yms.fintracker.di.scope.ActivityScope
import com.luigivampa92.yms.fintracker.ui.account.AccountActivity
import com.luigivampa92.yms.fintracker.ui.addrecord.AddRecordActivity
import com.luigivampa92.yms.fintracker.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [(AndroidSupportInjectionModule::class)])
abstract class ContextModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun mainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(AddRecordActivityModule::class)])
    abstract fun addRecordActivityInjector(): AddRecordActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(AccountActivityModule::class)])
    abstract fun accountActivityInjector(): AccountActivity
}