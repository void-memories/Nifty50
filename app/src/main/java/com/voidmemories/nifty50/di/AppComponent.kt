package com.voidmemories.nifty50.di

import com.voidmemories.nifty50.presentation.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}