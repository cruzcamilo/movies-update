package com.example.trendingmovies.screens.moviedetails

import com.example.trendingmovies.movies.MovieWithDetails
import com.example.trendingmovies.screens.common.mvcviews.ObservableViewMvc

interface MovieDetailsViewMvc: ObservableViewMvc<MovieDetailsViewMvc.Listener> {

    interface Listener {
    }

    fun bindMovie(movie: MovieWithDetails)
}