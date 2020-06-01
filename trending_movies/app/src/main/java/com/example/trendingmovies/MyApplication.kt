package com.example.trendingmovies

import android.app.Application
import androidx.annotation.UiThread
import com.example.trendingmovies.common.dependencyinjection.CompositionRoot
import com.example.trendingmovies.common.dependencyinjection.application.ApplicationComponent
import com.example.trendingmovies.common.dependencyinjection.application.ApplicationModule
import com.example.trendingmovies.common.dependencyinjection.application.DaggerApplicationComponent
import com.example.trendingmovies.movies.FetchMovieDetailsUseCase
import com.example.trendingmovies.movies.FetchMoviesListUseCase
import com.example.trendingmovies.networking.MovieDbApi
import dagger.Component
import dagger.internal.DaggerCollections
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    private lateinit var mApplicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule())
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent{
        return mApplicationComponent
    }
}