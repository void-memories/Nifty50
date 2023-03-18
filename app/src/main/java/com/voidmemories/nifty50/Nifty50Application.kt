package com.voidmemories.nifty50

import android.app.Application
import com.voidmemories.nifty50.di.AppComponent
import com.voidmemories.nifty50.di.DaggerAppComponent

class Nifty50Application : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().build()
    }
}