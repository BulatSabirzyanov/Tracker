package com.example.tracker

import com.example.tracker.domain.usecase.GetGameTournamentUseCase
import com.example.tracker.domain.GameRepository
import com.example.cytrack.data.remote.response.GameTournamentsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class GetGameTournamentUseCaseTest {

    private val mockGameRepository = Mockito.mock(GameRepository::class.java)
    private val getGameTournamentUseCase = GetGameTournamentUseCase(mockGameRepository)

    @Test
    fun `get game tournament should return expected result`() = runBlocking {
        val game = "game1"
        val expectedTournaments = listOf(GameTournamentsResponse()) // Use the actual expected response here

        Mockito.`when`(mockGameRepository.getGameTournaments(game)).thenReturn(expectedTournaments)

        val result = getGameTournamentUseCase.getGameTournament(game)

        assert(result == expectedTournaments)
    }
}
