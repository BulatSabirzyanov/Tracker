package com.example.tracker

import com.example.tracker.domain.usecase.GetGameTournamentUseCase
import com.example.tracker.domain.GameRepository
import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.cytrack.data.remote.response.League
import com.example.cytrack.data.remote.response.Live
import com.example.cytrack.data.remote.response.Match
import com.example.cytrack.data.remote.response.Videogame
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class GetGameTournamentUseCaseTest {

    private val mockGameRepository = Mockito.mock(GameRepository::class.java)
    private val getGameTournamentUseCase = GetGameTournamentUseCase(mockGameRepository)

    @Test
    fun `get game tournament should return expected result`() = runBlocking {
        val game = "csgo"
        val expectedTournaments = listOf(GameTournamentsResponse(
            beginAt = "2023-03-12T10:00:00Z",
            hasBracket = true,
            id = 10049,
            matches = listOf(
                Match(
                    beginAt = "2023-03-12T13:00:00Z",
                    detailedStats = true,
                    draw = false,
                    endAt = "",
                    forfeit = false,
                    gameAdvantage = null,
                    id = 733858,
                    live = Live(
                        opensAt = null,
                        supported = false,
                        url = null
                    ),
                    matchType = "best_of",
                    modifiedAt = "2023-02-22T14:41:28Z",
                    name = "Upper bracket quarterfinal 2: TBD vs TBD",
                    numberOfGames = 3,
                    originalScheduledAt = "2023-03-12T13:00:00Z",
                    rescheduled = false,
                    scheduledAt = "2023-03-12T13:00:00Z",
                    slug = "2023-03-12-349d0365-27cc-47fe-b803-77db7c1ff569",
                    status = "not_started",
                    tournamentId = 10049,
                    winnerId = null,
                    winnerType = "Team"
                ),
                Match(
                    beginAt = "2023-03-12T19:00:00Z",
                    detailedStats = true,
                    draw = false,
                    endAt = "",
                    forfeit = false,
                    gameAdvantage = null,
                    id = 733864,
                    live = Live(
                        opensAt = null,
                        supported = false,
                        url = null
                    ),
                    matchType = "best_of",
                    modifiedAt = "2023-02-22T14:41:26Z",
                    name = "Lower bracket round 1 match 2: TBD vs TBD",
                    numberOfGames = 3,
                    originalScheduledAt = "2023-03-12T19:00:00Z",
                    rescheduled = false,
                    scheduledAt = "2023-03-12T19:00:00Z",
                    slug = "2023-03-12-8813a966-dd97-47dc-a6ec-f3ae9210954c",
                    status = "not_started",
                    tournamentId = 10049,
                    winnerId = null,
                    winnerType = "Team"
                )
            ),
            name = "Playoffs",
            league = League(
                name = "European Pro League"
            ),
            slug = "dota-2-european-pro-league-7-2023-playoffs",
            teams = emptyList(),
            tier = "d",
            videogame = Videogame(
                id = 4,
                name = "Dota 2"
            )
        )) // Use the actual expected response here

        Mockito.`when`(mockGameRepository.getGameTournaments(game)).thenReturn(expectedTournaments)

        val result = getGameTournamentUseCase.getGameTournament(game)

        assert(result == expectedTournaments)
    }

    @Test
    fun `get game tournament returns expected data`() = runBlocking {
        // Arrange
        val game = "game"
        val expectedData = listOf<GameTournamentsResponse>() // Fill this with your expected data
        `when`(mockGameRepository.getGameTournaments(game)).thenReturn(expectedData)

        // Act
        val result = getGameTournamentUseCase.getGameTournament(game)

        // Assert
        assertEquals(expectedData, result)
        verify(mockGameRepository, times(1)).getGameTournaments(game)
    }
}
