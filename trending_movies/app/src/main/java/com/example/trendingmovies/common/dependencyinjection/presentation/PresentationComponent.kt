package com.example.trendingmovies.common.dependencyinjection.presentation

import com.example.trendingmovies.common.dependencyinjection.application.ApplicationComponent
import com.example.trendingmovies.screens.moviedetails.MovieDetailsActivity
import com.example.trendingmovies.screens.movielist.MovieListActivity
import dagger.Component

@PresentationScope
@Component(dependencies = [ApplicationComponent::class], modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(movieListActivity: MovieListActivity)
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}