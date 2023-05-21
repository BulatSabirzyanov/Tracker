package com.example.tracker

import android.app.Application
import android.content.Context
import com.example.tracker.di.DaggerAppComponent
import com.example.tracker.di.AppComponent



class CyberTrackerAppApplication
    : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }


}

fun Context.appComponent() = (this.applicationContext as CyberTrackerAppApplication).appComponent
