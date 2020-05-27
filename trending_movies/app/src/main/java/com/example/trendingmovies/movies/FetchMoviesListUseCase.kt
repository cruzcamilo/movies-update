package com.example.trendingmovies.movies

import com.example.trendingmovies.common.BaseObservable
import com.example.trendingmovies.networking.MovieDbApi
import com.example.trendingmovies.networking.MovieListResponseSchema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FetchMoviesListUseCase(var movieDbApi: MovieDbApi): BaseObservable<FetchMoviesListUseCase.Listener>() {

    interface Listener{
        fun onFetchOfMovieSucceeded(movies: List<Movie>)
        fun onFetchOfMovieFailed()
    }

    private val mMovieDbApi: MovieDbApi = movieDbApi
    private var mCall: Call<MovieListResponseSchema>? = null

    fun fetchLastMoviesAndNotify(){
        cancelCurrentFetchIfActive()
        mCall = mMovieDbApi.popularMoviesList()
        mCall?.enqueue(object : Callback<MovieListResponseSchema> {
            override fun onResponse(
                call: Call<MovieListResponseSchema>,
                response: Response<MovieListResponseSchema>
            ) {
                if(response.isSuccessful){
                    notifySucceeded(response.body()!!.getMovies())
                } else {
                    notifyFailed()
                }
            }

            override fun onFailure(call: Call<MovieListResponseSchema>, t: Throwable) {
                notifyFailed()
            }
        })
    }

    private fun cancelCurrentFetchIfActive() {
        if (mCall != null && !mCall!!.isCanceled && !mCall!!.isExecuted) {
            mCall!!.cancel()
        }
    }

    private fun notifySucceeded(questions: List<Movie>) {
        val unmodifiableQuestions: List<Movie> =
            Collections.unmodifiableList<Movie>(questions)
        for (listener in getListeners()) {
            listener.onFetchOfMovieSucceeded(unmodifiableQuestions)
        }
    }

    private fun notifyFailed() {
        for (listener in getListeners()) {
            listener.onFetchOfMovieFailed()
        }
    }

}