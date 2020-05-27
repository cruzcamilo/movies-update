package com.example.trendingmovies.screens.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.trendingmovies.movies.FetchMoviesDetailsUseCase
import com.example.trendingmovies.movies.MovieWithDetails

class MovieDetailsActivity : AppCompatActivity(), FetchMoviesDetailsUseCase.Listener {

    private val mMovieId: Int by lazy {
    intent.extras!!.getInt(MOVIE_ID)
    }
    private lateinit var mViewMvc: MovieDetailsViewMvc
    private lateinit var mFetchMovieDetailsUseCaseUseCase: FetchMoviesDetailsUseCase

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
        mViewMvc = MovieDetailsViewMvcImpl(LayoutInflater.from(this), null)
        setContentView(mViewMvc.getRootView())
        mFetchMovieDetailsUseCaseUseCase = FetchMoviesDetailsUseCase()
    }

    override fun onStart() {
        super.onStart()
//        mViewMvc.registerListener(this)
        mFetchMovieDetailsUseCaseUseCase.registerListener(this)
        mFetchMovieDetailsUseCaseUseCase.fetchLastMoviesAndNotify(mMovieId)
    }

    override fun onFetchOfMovieSucceeded(movie: MovieWithDetails) {
        mViewMvc.bindMovie(movie)
    }

    override fun onFetchOfMovieFailed() {
        //TODO("Not yet implemented")
    }
}