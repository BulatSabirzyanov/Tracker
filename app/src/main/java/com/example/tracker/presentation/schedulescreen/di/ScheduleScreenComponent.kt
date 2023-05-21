package com.example.tracker.presentation.schedulescreen.di

import com.example.tracker.presentation.schedulescreen.ScheduleScreenViewModel
import dagger.Component
@Component(
    modules = [ScheduleScreenModule::class]
)
interface ScheduleScreenComponent {

    @Component.Builder
    interface Builder {
        fun build(): ScheduleScreenComponent
    }

    fun getViewModel() : ScheduleScreenViewModel
}
