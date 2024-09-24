package com.example.peratrack.app

import android.app.Application
import com.example.peratrack.di.AppComponent
import com.example.peratrack.di.AppModule
import com.example.peratrack.di.DaggerAppComponent

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