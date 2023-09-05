package com.example.lab5mod4.data

import com.example.lab5mod4.data.models.Movie
import com.example.lab5mod4.data.models.PopularMovies
import com.example.lab5mod4.data.network.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.request.*

private const val POPULAR_MOVIES = "${BASE_URL}/popular"
class MoviesRepository(
    private val httpClient: HttpClient
) {
    suspend fun getPopularMovies(): Resource<List<Movie>> {
        return try{
            Resource.Success(
                httpClient.get<PopularMovies>{
                    url(POPULAR_MOVIES)
                }.results
            )
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}
