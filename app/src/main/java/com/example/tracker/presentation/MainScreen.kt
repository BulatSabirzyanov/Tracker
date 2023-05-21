package com.example.tracker.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.tracker.ui.theme.TrackerTheme


@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val viewModel: MainViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    TrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainContent(
                viewState = state,
                eventHandler = viewModel::event
            )
        }
    }

}

@Composable
fun MainContent(
    viewState: MainViewState,
    eventHandler: (MainEvent) -> Unit,
) {
    MatchScreen()
}



@Composable
fun MatchScreen(viewModel: MainViewModel = viewModel()) {

    val state by viewModel.state.collectAsState()

    LazyColumn {
        items(state.matches, key = { it.id }) { match ->
            MatchItem(team1Icon = match.team1Icon, team1Name = match.team1, matchDate = match.date, team2Name = match.team2, team2Icon = match.team2Icon)


        }
    }
}

@Composable
fun MatchItem(
    team1Icon: String,
    team1Name: String,
    matchDate: String,
    team2Name: String,
    team2Icon: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = team1Icon,
                contentDescription = null,
            )
            Text(text = team1Name)
        }

        Text(text = matchDate, modifier = Modifier.padding(horizontal = 36.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = team2Name,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            AsyncImage(
                model = team2Icon,
                contentDescription = null,
            )
        }
    }
}





