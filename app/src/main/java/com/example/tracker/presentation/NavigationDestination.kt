package com.example.tracker.presentation

sealed class NavigationDestination(val destination: String) {
    object ScheduleScreen : NavigationDestination("schedulescreen")
    object DiscriptionScreen : NavigationDestination("discriptionscreen")
}
