package com.example.trendingmovies.common.dependencyinjection.application

import com.example.trendingmovies.Constants
import com.example.trendingmovies.movies.FetchMovieDetailsUseCase
import com.example.trendingmovies.movies.FetchMoviesListUseCase
import com.example.trendingmovies.networking.MovieDbApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    fun getFetchMovieListUseCase(movieDbApi: MovieDbApi): FetchMoviesListUseCase {
        return FetchMoviesListUseCase(movieDbApi)
    }
}