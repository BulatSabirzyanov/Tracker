package com.example.tracker.presentation

data class GameModel(
    val id: Long,
    val date: String,
    val team1: String,
    val team2: String,
    val team1Icon: String,
    val team2Icon: String,
)
