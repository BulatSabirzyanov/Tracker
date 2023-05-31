package com.example.tracker

import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.tracker.data.remote.service.PandaScoreApiService
import com.example.tracker.data.repository.GameRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GameRepositoryImplTest {

    @Mock
    private lateinit var pandaScoreApiService: PandaScoreApiService

    private lateinit var gameRepositoryImpl: GameRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        gameRepositoryImpl = GameRepositoryImpl(pandaScoreApiService)
    }

    @Test
    fun `test getGameTournaments returns correct data`() = runBlocking {
        // Arrange
        val game = "game"
        val expectedData = listOf<GameTournamentsResponse>() // Fill this with your expected data
        `when`(pandaScoreApiService.getGameTournaments(game)).thenReturn(expectedData)

        // Act
        val result = gameRepositoryImpl.getGameTournaments(game)

        // Assert
        assertEquals(expectedData, result)
        verify(pandaScoreApiService, times(1)).getGameTournaments(game)
    }


    // Additional tests for getPlayerData and error scenarios can be added here
}
