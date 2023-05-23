package com.example.tracker.presentation.schedulescreen.di

import com.example.tracker.di.AppComponent
import com.example.tracker.presentation.schedulescreen.ScheduleScreenViewModel
import dagger.Component
@Component(
    modules = [ScheduleScreenModule::class],
    dependencies = [AppComponent::class]

)
@ScheduleScreenScope
interface ScheduleScreenComponent {

    @Component.Builder
    interface Builder {

        fun appComponent(appComponent: AppComponent):Builder
        fun build(): ScheduleScreenComponent
    }

    fun getViewModel() : ScheduleScreenViewModel
}
