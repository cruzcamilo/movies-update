package com.example.trendingmovies.common.dependencyinjection.presentation

import com.example.trendingmovies.screens.moviedetails.MovieDetailsActivity
import com.example.trendingmovies.screens.movielist.MovieListActivity
import dagger.Subcomponent


@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(movieListActivity: MovieListActivity)
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}