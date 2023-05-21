package com.example.tracker

import android.app.Application
import com.example.tracker.data.remote.service.PandaScoreApiService
import com.example.tracker.data.remote.service.PandaScoreService
import com.example.tracker.data.repository.GameRepositoryImpl
import com.example.tracker.domain.GameRepository
import com.example.tracker.presentation.MainViewModel

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

val apiModule = module {
    single<PandaScoreApiService> { PandaScoreService.instance }
}

val repositoryModule = module {
    single<GameRepository> { GameRepositoryImpl(get()) }
}
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

class CyberTrackerAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CyberTrackerAppApplication)
            modules(listOf(apiModule, repositoryModule, viewModelModule))
        }
    }
}


