package com.example.grupilem.data.remote

import com.example.grupilem.model.Movie
import com.example.grupilem.model.MovieDetail

class MovieRemoteDataSource {

    private val apiEndpoint: ApiEndpoint = ApiRetrofit.getRetrofitInstance().create(ApiEndpoint::class.java)

    suspend fun fetchNowPlayingMovies(): Result<List<Movie>> {
        val response = apiEndpoint.fetchNowPlayingMovies()

        return if (response.isSuccessful) {
            Result.success(response.body()?.results ?: emptyList())
        } else {
            Result.failure(Throwable(response.message()))
        }
    }

    suspend fun fetchUpcomingMovies() : Result<List<Movie>> {
        val response = apiEndpoint.fetchUpcomingMovies()

        return if(response.isSuccessful) {
            Result.success(response.body()?.results ?: emptyList())
        } else {
            Result.failure(Throwable(response.message()))
        }
    }

    suspend fun fetchPopularMovies() : Result<List<Movie>> {
        val response = apiEndpoint.fetchPopularMovies()

        return if(response.isSuccessful) {
            Result.success(response.body()?.results ?: emptyList())
        } else {
            Result.failure(Throwable(response.message()))
        }
    }

    suspend fun fetchTopRatedMovies() : Result<List<Movie>> {
        val response = apiEndpoint.fetchTopRatedMovies()

        return if(response.isSuccessful) {
            Result.success(response.body()?.results ?: emptyList())
        } else {
            Result.failure(Throwable(response.message()))
        }
    }

    suspend fun searchMovies(query: String): Result<List<Movie>> {
        val response = apiEndpoint.searchMovie(query = query)

        return if (response.isSuccessful) {
            Result.success(response.body()?.results ?: emptyList())
        } else {
            Result.failure(Throwable(response.message()))
        }
    }

    suspend fun fetchDetailMovie(movieId: Int) : Result<MovieDetail> {
        val response = apiEndpoint.fetchDetailMovie(movieId = movieId)

        return if (response.isSuccessful) {
            Result.success(response.body() ?: MovieDetail())
        } else {
            Result.failure(Throwable(response.message()))
        }
    }
}