package com.luigivampa92.yms.fintracker.di.module

import android.content.Context
import com.luigivampa92.yms.fintracker.routing.base.ContactRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

@Module
class RouterModule {

    private val cicerone = Cicerone.create()

    @Singleton
    @Provides
    fun provideRouter() = cicerone.router

    @Singleton
    @Provides
    fun provideNavigatorHolder() = cicerone.navigatorHolder

    @Singleton
    @Provides
    fun provideContactRouter(context: Context) = ContactRouter(context)
}