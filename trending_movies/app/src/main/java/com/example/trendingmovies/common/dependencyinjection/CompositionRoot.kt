package com.example.trendingmovies.common.dependencyinjection

import androidx.annotation.UiThread
import com.example.trendingmovies.Constants
import com.example.trendingmovies.movies.FetchMovieDetailsUseCase
import com.example.trendingmovies.movies.FetchMoviesListUseCase
import com.example.trendingmovies.networking.MovieDbApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CompositionRoot {

    private lateinit var okHttpClient: OkHttpClient
    private var mRetrofit: Retrofit? = null
    private var mMovieDbApi: MovieDbApi? = null

    private fun getOkHttpClient(): OkHttpClient {
        okHttpClient = OkHttpClient()
        return okHttpClient.newBuilder().addInterceptor{ chain ->
            val original = chain.request()
            val httpUrl = original.url()
            val newHttpUrl = httpUrl.newBuilder().addQueryParameter("api_key", Constants.API_KEY).build()
            val requestBuilder = original.newBuilder().url(newHttpUrl)
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()
    }

    @UiThread
    private fun getRetrofit(): Retrofit {
        if (mRetrofit == null){
            mRetrofit =  Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return mRetrofit!!
    }

    @UiThread
    private fun getMovieDbApi(): MovieDbApi {
        if(mMovieDbApi == null){
            mMovieDbApi = getRetrofit().create(MovieDbApi::class.java)
        }
        return mMovieDbApi!!
    }

    @UiThread
    fun getFetchMovieListUseCase(): FetchMoviesListUseCase {
        return FetchMoviesListUseCase(getMovieDbApi())
    }

    @UiThread
    fun getFetchMovieDetailsUseCase(): FetchMovieDetailsUseCase {
        return FetchMovieDetailsUseCase(getMovieDbApi())
    }
}