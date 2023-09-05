package com.example.lab5mod4.ui.movies

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lab5mod4.data.models.Movie

@Composable
fun MovieList(movies:List<Movie>) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(movies) { movie ->
                MovieItem(movie)
            }
        }
    }
}