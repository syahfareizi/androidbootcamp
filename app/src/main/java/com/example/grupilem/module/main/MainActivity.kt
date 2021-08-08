package com.example.grupilem.module.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.grupilem.MovieType
import com.example.grupilem.R
import com.example.grupilem.databinding.ActivityMainBinding
import com.example.grupilem.module.detail.DetailMovieActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieTypeListAdapter.MovieTypeListener,
    MovieListAdapter.MovieListListener {

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    val moviesAdapter by lazy { MovieListAdapter() }
    val movieTypeAdapter by lazy { MovieTypeListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
        setupDataBinding()
        setupObserver()
        setupListener()
        setupMovieType()

        viewModel.getNowPlayingMovies()

        movieTypeAdapter.setListener(this)
        moviesAdapter.setListener(this)
    }

    private fun setupDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
    }

    private fun setupListener() {
        searchEditText.doAfterTextChanged {
            viewModel.searchMovies(it.toString())
        }
    }

    private fun setupObserver() {
        viewModel.moviesResultLiveData.observe(this) {
            viewModel.handleMovieListResponse(it)
        }
        viewModel.moviesLiveData.observe(this) {
            moviesAdapter.replaceData(it)
        }
        viewModel.errorMessageLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.setContext(this)
    }

    private fun setupMovieType() {
        movieTypeAdapter.replaceData(
            listOf(
                MovieType.NOW_PLAYING,
                MovieType.UPCOMING,
                MovieType.LATEST,
                MovieType.POPULAR,
                MovieType.TOP_RATED
            )
        )
    }

    override fun onMovieTypeClicked(id: Int) {
        when(id) {
            MovieType.NOW_PLAYING.id -> viewModel.getNowPlayingMovies()
            MovieType.UPCOMING.id -> viewModel.getUpcomingMovies()
            MovieType.POPULAR.id -> viewModel.getPopularMovies()
            MovieType.TOP_RATED.id -> viewModel.getTopRatedMovies()
        }
    }

    override fun onMovieClicked(id: Int) {
        val intent = Intent(this, DetailMovieActivity::class.java).apply {
            putExtra("movieID", id)
        }
        startActivity(intent)
    }
}