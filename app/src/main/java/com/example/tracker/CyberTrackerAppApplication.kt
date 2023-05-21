package com.example.tracker

import android.app.Application
import com.example.tracker.di.DaggerAppComponent
import com.example.tracker.di.AppComponent



class CyberTrackerAppApplication
    : Application() {

    val appComponent: AppComponent = createAppComponent()

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}


