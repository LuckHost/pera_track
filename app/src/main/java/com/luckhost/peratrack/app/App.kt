package com.luckhost.peratrack.app

import android.app.Application
import com.luckhost.peratrack.di.AppComponent
import com.luckhost.peratrack.di.AppModule
import com.luckhost.peratrack.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}