package com.example.tracker.presentation.schedulescreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.navigation.NavController
import com.example.cytrack.data.remote.response.Match


@Composable
fun ScheduleScreen(
    navController: NavController,
    viewModel: ScheduleScreenViewModel
) {


    val state by viewModel.state.collectAsState()

    LazyColumn {
        items(state.matches, key = { it.id }) { match ->
            MatchItem(
                team1Icon = match.team1Icon,
                team1Name = match.team1,
                matchDate = match.date,
                team2Name = match.team2,
                team2Icon = match.team2Icon
            )


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
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,

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





