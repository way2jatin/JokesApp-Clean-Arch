package com.unlimit.presentation.jokes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unlimit.domain.model.Joke
import com.unlimit.presentation.util.JokeText

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun JokesContent(viewModel: JokesViewModel) {

    val jokes: List<Joke> by viewModel.jokesData.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jokes") }
            )
        }
    ) {
        Column {
            LazyColumn {
                jokes.forEach { joke ->
                    item {
                        Card(
                            modifier = Modifier.padding(16.dp).fillMaxSize(),
                            elevation = 4.dp
                        ) {
                            JokeText(joke.joke)
                        }
                    }
                }
            }
        }
    }
}
