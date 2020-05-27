package com.example.trendingmovies.movies

import com.example.trendingmovies.BuildConfig
import com.example.trendingmovies.Constants
import com.example.trendingmovies.common.BaseObservable
import com.example.trendingmovies.networking.MovieDbApi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchMoviesDetailsUseCase: BaseObservable<FetchMoviesDetailsUseCase.Listener>() {

    interface Listener{
        fun onFetchOfMovieSucceeded(movie: MovieWithDetails)
        fun onFetchOfMovieFailed()
    }

    private val mMovieDbApi:MovieDbApi
    private var mCall : Call<MovieWithDetails>? = null
    private lateinit var okHttpClient: OkHttpClient

    private val API_KEY = BuildConfig.API_KEY

    private fun getOkHttpClient(): OkHttpClient{
        okHttpClient = OkHttpClient()
        return okHttpClient.newBuilder().addInterceptor{ chain ->
            val original = chain.request()
            val httpUrl = original.url()
            val newHttpUrl = httpUrl.newBuilder().addQueryParameter("api_key", API_KEY).build()
            val requestBuilder = original.newBuilder().url(newHttpUrl)
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()
    }

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mMovieDbApi = retrofit.create(MovieDbApi::class.java)
    }

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