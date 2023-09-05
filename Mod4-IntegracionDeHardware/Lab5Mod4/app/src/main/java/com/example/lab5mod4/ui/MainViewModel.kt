package com.example.lab5mod4.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5mod4.data.MoviesRepository
import com.example.lab5mod4.data.Resource
import com.example.lab5mod4.data.models.Movie
import com.example.lab5mod4.data.network.TmdbHttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val httpClient = TmdbHttpClient()
    private val repository = MoviesRepository(httpClient.getHttpClient())
    private val _movies = MutableStateFlow<Resource<List<Movie>>?>(value = null)
    val movies: StateFlow<Resource<List<Movie>>?> = _movies
    init {
        viewModelScope.launch {
            _movies.value = Resource.Loading
            _movies.value = repository.getPopularMovies()
        }
    }
}