package com.example.trendingmovies.movies

import com.example.trendingmovies.BuildConfig
import com.example.trendingmovies.Constants
import com.example.trendingmovies.common.BaseObservable
import com.example.trendingmovies.networking.MovieDbApi
import com.example.trendingmovies.networking.MovieListResponseSchema
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class FetchMoviesListUseCase: BaseObservable<FetchMoviesListUseCase.Listener>() {

    interface Listener{
        fun onFetchOfMovieSucceeded(movies: List<Movie>)
        fun onFetchOfMovieFailed()
    }

    private val mMovieDbApi:MovieDbApi
    var mCall: Call<MovieListResponseSchema>? = null
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