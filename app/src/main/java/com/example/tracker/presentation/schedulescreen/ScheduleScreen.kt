package com.example.tracker.presentation.schedulescreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.navigation.NavController
import com.example.cytrack.data.remote.response.Match
import com.example.tracker.ui.custom.ItisTheme

@Composable
fun ScheduleScreen(
    viewModel: ScheduleScreenViewModel,
    navController: NavController
) {
    val state = viewModel.state.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ItisTheme.colors.primaryBackground),
    ){items(state.value.matches,key = {it.id}){
        MatchItem(team1Icon = it.team1Icon, team1Name = it.team1, matchDate = it.date, team2Name = it.team2, team2Icon = it.team2Icon,onClick = { navController.navigate("detail_screen/${it.id}") })

    }

    }

}

@Composable
fun MatchItem(
    team1Icon: String,
    team1Name: String,
    matchDate: String,
    team2Name: String,
    team2Icon: String,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(

                model = team1Icon,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 8.dp)
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
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}





