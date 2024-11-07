package com.luckhost.peratrack.di

import com.luckhost.peratrack.presentation.authScreen.AuthorizationActivity
import com.luckhost.peratrack.presentation.mainScreen.chartWithListFragment.ChartWithListFragment
import com.luckhost.peratrack.presentation.mainScreen.MainActivity
import com.luckhost.peratrack.presentation.mainScreen.receiptCreatingFragment.ReceiptCreatingFragment
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(authActivity: AuthorizationActivity)
    fun inject(chartWithListFragment: ChartWithListFragment)
    fun inject(receiptCreatingFragment: ReceiptCreatingFragment)
}