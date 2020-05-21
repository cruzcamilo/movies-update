package com.example.trendingmovies.screens.movielist

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.trendingmovies.movies.FetchMoviesListUseCase
import com.example.trendingmovies.movies.Movie

class MovieListActivity : AppCompatActivity(), MovieListViewMvc.Listener,
    FetchMoviesListUseCase.Listener {

    private val mViewMvc by lazy {
        MovieListViewMvcImpl(LayoutInflater.from(this), null)
    }

    private val mFetchMoviesListUseCase by lazy {
        FetchMoviesListUseCase()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        mViewMvc.unRegisterListener(this)
    }

    override fun onMovieClicked(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFetchOfMovieSucceded(movies: List<Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFetchOfMovieFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
