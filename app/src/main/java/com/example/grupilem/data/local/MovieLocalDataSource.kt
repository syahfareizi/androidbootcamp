package com.example.grupilem.data.local

import android.content.Context
import com.example.grupilem.model.MovieDetail

class MovieLocalDataSource(context: Context) {

    private val dao: MovieDao = MovieDatabase.provideDatabase(context).movieDetailDao()

    fun saveMovieDetail(movie: MovieDetail) {
        dao.insertMovieDetail(movie)
    }

    fun getMovieDetailById(movieId: Int) = dao.getDetailMovie(movieId)
}