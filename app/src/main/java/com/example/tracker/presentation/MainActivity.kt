package com.example.tracker.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tracker.ui.custom.ItisTheme
import com.example.tracker.appComponent
import com.example.tracker.presentation.schedulescreen.DetailScreen
import com.example.tracker.presentation.schedulescreen.ScheduleScreen
import com.example.tracker.presentation.schedulescreen.ScheduleScreenViewModel
import com.example.tracker.presentation.schedulescreen.di.DaggerScheduleScreenComponent
import com.example.tracker.presentation.settings.LocalSettingsEventBus
import com.example.tracker.presentation.settings.SettingsEventBus
import com.example.tracker.presentation.utils.daggerViewModel
import com.example.tracker.ui.theme.TrackerTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val settingsEventBus = remember { SettingsEventBus() }

            val currentSettings = settingsEventBus.currentSettings.collectAsState().value
            ItisTheme(
                style = currentSettings.style,
                darkTheme = currentSettings.isDarkMode,
                corners = currentSettings.cornerStyle,
                textSize = currentSettings.textSize,
                paddingSize = currentSettings.paddingSize
            ) {
                CompositionLocalProvider(
                    LocalSettingsEventBus provides settingsEventBus
                ) {
                    Log.i("COMPNAVILOG", "Navigation Screen1")

                    val component =
                        DaggerScheduleScreenComponent.builder().appComponent(appComponent()).build()

                    val viewModel: ScheduleScreenViewModel = daggerViewModel {
                        Log.i("COMPNAVILOG", "create VM: Screen1ViewModel")
                        component.getViewModel()
                    }

                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "schedule_screen") {
                        composable("schedule_screen") {
                            ScheduleScreen(viewModel = viewModel, navController = navController)
                        }
                        composable("detail_screen/{matchId}") { backStackEntry ->
                            val matchId = backStackEntry.arguments?.getString("matchId")?.toLongOrNull()
                            if (matchId != null) {
                                DetailScreen(matchId, viewModel)
                            } else {

                            }
                        }
                    }

                }

            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrackerTheme {
        Greeting("Android")
    }
}

