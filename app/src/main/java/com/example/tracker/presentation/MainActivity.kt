package com.example.tracker.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tracker.presentation.schedulescreen.ScheduleScreen
import com.example.tracker.presentation.schedulescreen.ScheduleScreenViewModel
import com.example.tracker.presentation.utils.daggerViewModel
import com.example.tracker.ui.theme.TrackerTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val navController = rememberNavController()

            NavHost(
                navController,
                startDestination = NavigationDestination.ScheduleScreen.destination
            ) {
                composable(NavigationDestination.ScheduleScreen.destination) {

                    Log.i("COMPNAVILOG", "Navigation Screen1")


                    val component = DaggerScheduleScreenComponent.builder().build()

                    val viewModel: ScheduleScreenViewModel = daggerViewModel {
                        Log.i("COMPNAVILOG", "create VM: Screen1ViewModel")
                        component.getViewModel()
                    }
                    ScheduleScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
                composable(NavigationDestination.DiscriptionScreen.destination) {
                    Log.i("COMPNAVILOG", "Navigation Screen2")
                    val viewModel: DiscriptionScreenViewModel = daggerViewModel {
                        DaggerDiscriptionScreenComponent.builder().build().getViewModel()
                    }

                    DiscriptionScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
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
}
