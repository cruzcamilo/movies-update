package com.example.trendingmovies.screens.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.trendingmovies.movies.FetchMovieDetailsUseCase
import com.example.trendingmovies.movies.MovieWithDetails
import com.example.trendingmovies.movies.Poster
import com.example.trendingmovies.screens.common.activities.BaseActivity
import com.example.trendingmovies.screens.common.dialogs.DialogsManager
import com.example.trendingmovies.screens.common.dialogs.ServerErrorDialogFragment
import com.example.trendingmovies.screens.common.mvcviews.ViewMvcFactory
import javax.inject.Inject

class MovieDetailsActivity : BaseActivity(), MovieDetailsViewMvc.Listener,
    FetchMovieDetailsUseCase.Listener {

    private val mMovieId: Int by lazy {
    intent.extras!!.getInt(MOVIE_ID)
    }
    @Inject lateinit var mDialogsManager: DialogsManager
    @Inject lateinit var mFetchMovieDetailsUseCase: FetchMovieDetailsUseCase
    @Inject lateinit var mViewMvcFactory: ViewMvcFactory

    private lateinit var mViewMvc: MovieDetailsViewMvc

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
        getPresentationComponent().inject(this)
        mViewMvc = mViewMvcFactory.newInstance(MovieDetailsViewMvc::class, null)
        setContentView(mViewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
        mFetchMovieDetailsUseCase.registerListener(this)
        mFetchMovieDetailsUseCase.fetchLastMoviesAndNotify(mMovieId)

    }

    override fun onFetchOfMovieSucceeded(movie: MovieWithDetails) {
        mViewMvc.bindMovie(movie)
    }

    override fun onFetchOfMovieFailed() {
        mDialogsManager.showDialogWithId(ServerErrorDialogFragment.newInstance(), "")
    }

    override fun onFetchOfPosterSucceeded(posterList: List<Poster>) {
        mViewMvc.bindMoviePoster(posterList)
    }

    override fun onFetchOfPosterFailed() {
        //TODO("Not yet implemented")
    }
}