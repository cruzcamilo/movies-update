package com.example.trendingmovies

import android.app.Application
import androidx.annotation.UiThread
import com.example.trendingmovies.common.dependencyinjection.CompositionRoot
import com.example.trendingmovies.movies.FetchMovieDetailsUseCase
import com.example.trendingmovies.movies.FetchMoviesListUseCase
import com.example.trendingmovies.networking.MovieDbApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    private lateinit var mCompositionRoot: CompositionRoot

    override fun onCreate() {
        super.onCreate()
        mCompositionRoot = CompositionRoot()
    }

    fun getCompositionRoot(): CompositionRoot{
        return mCompositionRoot
    }
}