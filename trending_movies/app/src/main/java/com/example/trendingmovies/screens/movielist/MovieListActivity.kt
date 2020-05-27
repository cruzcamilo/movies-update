package com.example.trendingmovies.screens.movielist

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.trendingmovies.movies.FetchMoviesListUseCase
import com.example.trendingmovies.movies.Movie
import com.example.trendingmovies.screens.moviedetails.MovieDetailsActivity

class MovieListActivity : AppCompatActivity(), MovieListViewMvc.Listener,
    FetchMoviesListUseCase.Listener {

    private lateinit var mViewMvc: MovieListViewMvc
    private val mFetchMoviesListUseCase by lazy {
        FetchMoviesListUseCase()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewMvc = MovieListViewMvcImpl(LayoutInflater.from(this), null)
        setContentView(mViewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
        mFetchMoviesListUseCase.registerListener(this)
        mFetchMoviesListUseCase.fetchLastMoviesAndNotify()
    }

    override fun onStop() {
        super.onStop()
        mViewMvc.unRegisterListener(this)
        mFetchMoviesListUseCase.unRegisterListener(this)
    }

    override fun onFetchOfMovieSucceeded(movies: List<Movie>) {
        mViewMvc.bindMovies(movies)
    }

    override fun onMovieClicked(movie: Movie) {
        MovieDetailsActivity.start(this@MovieListActivity, movie.id.toInt())
    }

    override fun onFetchOfMovieFailed() {
        //TODO("not implemented")
    }
}
