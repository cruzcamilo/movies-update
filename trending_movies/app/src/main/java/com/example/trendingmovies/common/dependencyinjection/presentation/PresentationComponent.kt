package com.example.trendingmovies.common.dependencyinjection.presentation

import com.example.trendingmovies.movies.FetchMovieDetailsUseCase
import com.example.trendingmovies.movies.FetchMoviesListUseCase
import com.example.trendingmovies.screens.common.dialogs.DialogsManager
import com.example.trendingmovies.screens.common.mvcviews.ViewMvcFactory
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {
    fun getDialogsManager(): DialogsManager
    fun getViewMvcFactory(): ViewMvcFactory
    fun getFetchMoviesListUseCase(): FetchMoviesListUseCase
    fun getFetchMoviesDetailsUseCase(): FetchMovieDetailsUseCase
}