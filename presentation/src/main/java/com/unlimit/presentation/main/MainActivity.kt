package com.unlimit.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.unlimit.presentation.jokes.JokesContent
import com.unlimit.presentation.jokes.JokesViewModel
import com.unlimit.presentation.state.AppState
import com.unlimit.presentation.util.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private val jokesViewModel: JokesViewModel by inject()

    // Coroutine context
    private val coroutineContext: CoroutineContext = Dispatchers.Default + SupervisorJob()
    private val scope = CoroutineScope(coroutineContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isNetworkAvailable = NetworkState.isAvailable(this)

        scope.launch {
            while (true) {
                jokesViewModel.getJokes(isNetworkAvailable)
                delay(60000L) // Delay for 60 seconds
            }
        }

        setContent {
            MaterialTheme(colors = AppState.theme) {
                JokeContent(jokesViewModel)
            }
        }
    }
}

@Composable
fun JokeContent(jokesViewModel: JokesViewModel) {
    Surface(color = MaterialTheme.colors.background) {
        val systemUiController = rememberSystemUiController()

        LaunchedEffect(key1 = true, block = {
            systemUiController.setStatusBarColor(
                color = AppState.theme.surface
            )
        })

        JokesContent(viewModel = jokesViewModel)
    }
}