package com.example.tracker.presentation.schedulescreen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cytrack.data.remote.response.Match
import com.example.tracker.domain.GameRepository
import com.example.tracker.presentation.GameModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@Immutable
data class MainViewState(
    val matches: List<GameModel> = listOf()
)

sealed interface MainEvent {
    data class OnMatchClick(val match: Match) : MainEvent
}

sealed interface MainAction {
    object Navigate : MainAction
}

class ScheduleScreenViewModel(private val gameRepository: GameRepository) : ViewModel() {


    private val _state = MutableStateFlow(MainViewState())
    val state: StateFlow<MainViewState> = _state


    init {
        getSchedule("csgo")
    }

    fun event(mainEvent: MainEvent) {
        when (mainEvent) {
            is MainEvent.OnMatchClick -> TODO()

        }
    }

    private fun getSchedule(game: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val value = gameRepository.getGameTournaments(game)
            val filteredTournaments = value.filter { !it.hasBracket }
            val gameModels = filteredTournaments.flatMap { tournament ->
                tournament.matches.mapNotNull { match ->


                    if (match.status != "finished" && match.status != "canceled") {

                        val teams = match.name.split(" ")

                        val team1 =
                            tournament.teams.find { it.name == teams.getOrNull(teams.lastIndex - 2) }
                        val team2 = tournament.teams.find { it.name == teams.last() }

                        if (team1 != null && team2 != null) {
                            GameModel(
                                id = match.tournamentId.toLong(),
                                date = match.beginAt,
                                team1 = team1.name,
                                team2 = team2.name,
                                team1Icon = team1.imageUrl,
                                team2Icon = team2.imageUrl,
                            )
                        } else {
                            null
                        }
                    } else {
                        null
                    }
                }
            }
            _state.value = _state.value.copy(matches = gameModels)
        }

    }
}



