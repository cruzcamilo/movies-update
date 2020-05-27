package com.example.trendingmovies.movies

import com.example.trendingmovies.common.BaseObservable
import com.example.trendingmovies.networking.MovieDbApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchMovieDetailsUseCase(var movieDbApi: MovieDbApi): BaseObservable<FetchMovieDetailsUseCase.Listener>() {

    interface Listener{
        fun onFetchOfMovieSucceeded(movie: MovieWithDetails)
        fun onFetchOfMovieFailed()
    }

    private val mMovieDbApi: MovieDbApi = movieDbApi
    private var mCall : Call<MovieWithDetails>? = null

    fun fetchLastMoviesAndNotify(movieId: Int){
        cancelCurrentFetchIfActive()
        mCall = mMovieDbApi.movieDetails(movieId)
        mCall?.enqueue(object : Callback<MovieWithDetails> {
            override fun onResponse(
                call: Call<MovieWithDetails>,
                response: Response<MovieWithDetails>
            ) {
                if (response.isSuccessful){
                    notifySucceeded(response.body()!!)
                }
            }

            override fun onFailure(call: Call<MovieWithDetails>, t: Throwable) {
                notifyFailed()
            }
        })
    }

    private fun cancelCurrentFetchIfActive() {
        if (mCall != null && !mCall!!.isCanceled && !mCall!!.isExecuted) {
            mCall?.cancel()
        }
    }

    private fun notifySucceeded(movie: MovieWithDetails) {
        for (listener in getListeners()) {
            listener.onFetchOfMovieSucceeded(movie)
        }
    }

    private fun notifyFailed() {
        for (listener in getListeners()) {
            listener.onFetchOfMovieFailed()
        }
    }
}