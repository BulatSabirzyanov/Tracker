package com.example.tracker.data.repository

import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.cytrack.data.remote.response.PlayersResponse
import com.example.tracker.domain.GameRepository
import com.example.tracker.data.remote.service.PandaScoreApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GameRepositoryImpl @Inject constructor(
    private val remoteSource: PandaScoreApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) :
    GameRepository {

    override suspend fun getGameTournaments(game: String): List<GameTournamentsResponse> {
        return withContext(ioDispatcher) {
            remoteSource.getGameTournaments(game)
        }

    }

    override suspend fun getPlayerData(game: String): List<PlayersResponse> {
        return withContext(ioDispatcher){
            remoteSource.getPlayerData(game)
        }
    }


}
