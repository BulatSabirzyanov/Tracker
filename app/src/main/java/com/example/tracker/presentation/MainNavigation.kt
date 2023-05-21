package com.example.tracker.presentation




sealed class Screen(
    val route: String,
    val name: String
){
    object Main : Screen(
        route = "main",
        name = "R.string.screen_main"

    )

    object Cart : Screen(
        route = "cart",
        name = "R.string.screen_cart"

    )
}

