package com.example.lab5mod4.ui

import AppTheme
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab5mod4.data.Resource
import com.example.lab5mod4.ui.theme.Lab5Mod4Theme
import com.example.lab5mod4.ui.movies.MovieList

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val movies = viewModel.movies.collectAsState()

            AppTheme {
                movies.value?.let {
                    when(it){
                        is Resource.Failure->{
                            Toast.makeText(context,it.exception.message!!, Toast.LENGTH_LONG).show()
                        }
                        Resource.Loading -> {
                            //@todo create progressbar here
                        }
                        is Resource.Success -> {
                            MovieList(it.result)
                        }
                    }
                }
            }
        }
    }
}

