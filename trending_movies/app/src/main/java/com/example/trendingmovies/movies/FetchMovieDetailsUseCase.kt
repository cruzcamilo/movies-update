package com.example.trendingmovies.movies

import com.example.trendingmovies.common.BaseObservable
import com.example.trendingmovies.networking.MovieDbApi
import com.example.trendingmovies.networking.PosterListResponseSchema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchMovieDetailsUseCase(var movieDbApi: MovieDbApi): BaseObservable<FetchMovieDetailsUseCase.Listener>() {

    interface Listener{
        fun onFetchOfMovieSucceeded(movie: MovieWithDetails)
        fun onFetchOfMovieFailed()
        fun onFetchOfPosterSucceeded(posterList: List<Poster>)
        fun onFetchOfPosterFailed()
    }

    private val mMovieDbApi: MovieDbApi = movieDbApi
    private var mMovieCall : Call<MovieWithDetails>? = null
    private var mPosterCall: Call<PosterListResponseSchema>? = null

    fun fetchLastMoviesAndNotify(movieId: Int){
        cancelCurrentFetchIfActive()
        mMovieCall = mMovieDbApi.movieDetails(movieId)
        mMovieCall?.enqueue(object : Callback<MovieWithDetails> {
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

        mPosterCall = mMovieDbApi.moviePoster(movieId)
        mPosterCall?.enqueue(object : Callback<PosterListResponseSchema>{

            override fun onResponse(
                call: Call<PosterListResponseSchema>,
                response: Response<PosterListResponseSchema>
            ) {
                if(response.isSuccessful){
                    posterNotifySucceeded(response.body()?.getPosters())
                }
            }

            override fun onFailure(call: Call<PosterListResponseSchema>, t: Throwable) {
                posterNotifyFailed()
            }
        })
    }

    private fun cancelCurrentFetchIfActive() {
        if (mMovieCall != null && !mMovieCall!!.isCanceled && !mMovieCall!!.isExecuted) {
            mMovieCall?.cancel()
        }

        if (mPosterCall != null && !mPosterCall!!.isCanceled && !mPosterCall!!.isExecuted) {
            mPosterCall?.cancel()
        }
    }

    private fun posterNotifySucceeded(posterList: List<Poster>?) {
        for (listener in getListeners()) {
            listener.onFetchOfPosterSucceeded(posterList!!)
        }
    }

    private fun posterNotifyFailed() {
        for (listener in getListeners()) {
            listener.onFetchOfPosterFailed()
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