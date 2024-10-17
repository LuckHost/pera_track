package com.luckhost.peratrack.di

import com.luckhost.peratrack.presentation.mainScreen.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}