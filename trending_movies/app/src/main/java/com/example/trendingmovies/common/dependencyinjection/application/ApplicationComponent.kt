package com.example.trendingmovies.common.dependencyinjection.application

import com.example.trendingmovies.movies.FetchMovieDetailsUseCase
import com.example.trendingmovies.movies.FetchMoviesListUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun getFetchMoviesListUseCase(): FetchMoviesListUseCase
    fun getFetchMoviesDetailsUseCase(): FetchMovieDetailsUseCase
}