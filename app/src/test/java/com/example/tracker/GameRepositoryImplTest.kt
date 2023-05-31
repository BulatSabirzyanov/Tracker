package com.example.tracker

import com.example.cytrack.data.remote.response.GameTournamentsResponse
import com.example.tracker.data.remote.service.PandaScoreApiService
import com.example.tracker.data.repository.GameRepositoryImpl
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

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
        val game = "csgo"
        val expectedData = listOf<GameTournamentsResponse>() // Fill this with your expected data
        `when`(pandaScoreApiService.getGameTournaments(game)).thenReturn(expectedData)

        // Act
        val result = gameRepositoryImpl.getGameTournaments(game)

        // Assert
        assertEquals(expectedData, result)
        verify(pandaScoreApiService, times(1)).getGameTournaments(game)
    }


    @Test(expected = HttpException::class)
    fun `get game tournaments throws HttpException on 401 error`() = runBlocking {
        // Arrange
        val game = "game"
        val exception = HttpException(
            Response.error<GameTournamentsResponse>(
                401,
                "".toResponseBody("application/json".toMediaTypeOrNull())
            )
        )
        val mockPandaScoreApiService = Mockito.mock(PandaScoreApiService::class.java)
        `when`(mockPandaScoreApiService.getGameTournaments(game)).thenThrow(exception)

        val gameRepositoryImpl = GameRepositoryImpl(mockPandaScoreApiService)

        // Act
        gameRepositoryImpl.getGameTournaments(game)

        // Assert is handled by the expected exception
    }

    // Additional tests for getPlayerData and error scenarios can be added here
}
