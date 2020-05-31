package com.example.trendingmovies.screens.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.trendingmovies.movies.FetchMovieDetailsUseCase
import com.example.trendingmovies.movies.MovieWithDetails
import com.example.trendingmovies.screens.common.activities.BaseActivity
import com.example.trendingmovies.screens.common.dialogs.DialogsManager
import com.example.trendingmovies.screens.common.dialogs.ServerErrorDialogFragment
import com.example.trendingmovies.screens.common.mvcviews.ViewMvcFactory

class MovieDetailsActivity : BaseActivity(), MovieDetailsViewMvc.Listener,
    FetchMovieDetailsUseCase.Listener {

    private val mMovieId: Int by lazy {
    intent.extras!!.getInt(MOVIE_ID)
    }
    lateinit var mViewMvc: MovieDetailsViewMvc
    lateinit var mDialogsManager: DialogsManager
    lateinit var mFetchMovieDetailsUseCaseUseCase: FetchMovieDetailsUseCase
    lateinit var mViewMvcFactory: ViewMvcFactory

    companion object {
        const val MOVIE_ID = "EXTRA_QUESTION_ID"

        fun start(context: Context, movieId: Int){
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_ID, movieId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInjector().inject(this)
        mViewMvc = mViewMvcFactory.newInstance(MovieDetailsViewMvc::class, null)
        setContentView(mViewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
        mFetchMovieDetailsUseCaseUseCase.registerListener(this)
        mFetchMovieDetailsUseCaseUseCase.fetchLastMoviesAndNotify(mMovieId)
    }

    override fun onFetchOfMovieSucceeded(movie: MovieWithDetails) {
        mViewMvc.bindMovie(movie)
    }

    override fun onFetchOfMovieFailed() {
        mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "")
    }
}