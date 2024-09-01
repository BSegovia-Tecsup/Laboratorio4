package com.example.moviecounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviecounter.ui.theme.MovieCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Greeting(name = "Android")
                        MovieCounter(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MovieCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) } // Persistencia del contador
    var movieName by rememberSaveable { mutableStateOf("") } // Persistencia del nombre de la película
    var movieList by rememberSaveable { mutableStateOf(listOf<String>()) } // Lista de películas

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "You have added $count movies.")
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para ingresar el nombre de la película
        TextField(
            value = movieName,
            onValueChange = { movieName = it },
            label = { Text("Movie Name") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar la película
        Button(onClick = {
            if (movieName.isNotBlank()) {
                movieList = movieList + movieName // Agregar la película a la lista
                count++ // Incrementar el contador
                movieName = "" // Limpiar el campo de texto
            }
        }) {
            Text("Add Movie")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la lista de películas
        MovieList(movieList)
    }
}

@Composable
fun MovieList(movies: List<String>) {
    Column {
        for (movie in movies) {
            Text(text = movie, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieCounterTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieCounter() {
    MovieCounter()
}
