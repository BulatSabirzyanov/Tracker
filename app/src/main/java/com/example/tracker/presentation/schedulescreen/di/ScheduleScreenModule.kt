package com.example.tracker.presentation.schedulescreen.di


import com.example.tracker.domain.GameRepository
import com.example.tracker.presentation.schedulescreen.ScheduleScreenViewModel
import dagger.Module
import dagger.Provides

@Module
class ScheduleScreenModule {


    @Provides
    @ScheduleScreenScope
    fun provideViewModel(gameRepository: GameRepository): ScheduleScreenViewModel = ScheduleScreenViewModel(gameRepository)
}
