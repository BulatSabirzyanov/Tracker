package com.example.tracker.domain.usecase

import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.tracker.domain.GameRepository
import javax.inject.Inject

class GetGameTournamentUseCase @Inject constructor(private val gameRepository: GameRepository) {
    suspend  fun getGameTournament(game: String): List<GameTournamentsResponse> {
        return gameRepository.getGameTournaments(game = game)

    }
}
