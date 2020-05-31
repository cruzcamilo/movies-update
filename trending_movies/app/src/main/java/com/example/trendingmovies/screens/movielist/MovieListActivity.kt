package com.example.trendingmovies.screens.movielist

import android.os.Bundle
import android.view.LayoutInflater
import com.example.trendingmovies.movies.FetchMoviesListUseCase
import com.example.trendingmovies.movies.Movie
import com.example.trendingmovies.screens.common.activities.BaseActivity
import com.example.trendingmovies.screens.common.dialogs.DialogsManager
import com.example.trendingmovies.screens.common.dialogs.ServerErrorDialogFragment
import com.example.trendingmovies.screens.common.mvcviews.ViewMvcFactory
import com.example.trendingmovies.screens.moviedetails.MovieDetailsActivity

class MovieListActivity : BaseActivity(), MovieListViewMvc.Listener,
    FetchMoviesListUseCase.Listener {

    lateinit var mViewMvc: MovieListViewMvc
    lateinit var mDialogsManager: DialogsManager
    lateinit var mFetchMoviesListUseCase: FetchMoviesListUseCase
    lateinit var mViewMvcFactory: ViewMvcFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInjector().inject(this)

        mViewMvc = mViewMvcFactory.newInstance(MovieListViewMvc::class, null)

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
        mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "")
    }
}