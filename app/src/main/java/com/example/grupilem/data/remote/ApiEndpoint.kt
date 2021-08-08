package com.example.grupilem.data.remote

import com.example.grupilem.model.MovieDetail
import com.example.grupilem.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovies(
        @Query("api_key") apiKey: String = ApiKey.MOVIE_API_KEY
    ): Response<MovieListResponse>

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
        @Query("api_key") apiKey: String = ApiKey.MOVIE_API_KEY
    ): Response<MovieListResponse>

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("api_key") apiKey: String = ApiKey.MOVIE_API_KEY
    ): Response<MovieListResponse>

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("api_key") apiKey: String = ApiKey.MOVIE_API_KEY
    ): Response<MovieListResponse>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String = ApiKey.MOVIE_API_KEY,
        @Query("query") query: String
    ): Response<MovieListResponse>

    @GET("movie/{movieId}")
    suspend fun fetchDetailMovie(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = ApiKey.MOVIE_API_KEY
    ): Response<MovieDetail>
}