package com.example.trendingmovies.screens.moviedetails

import com.example.trendingmovies.movies.MovieWithDetails
import com.example.trendingmovies.screens.mvcviews.ObservableViewMvc

interface MovieDetailsViewMvc: ObservableViewMvc<MovieDetailsViewMvc.Listener> {

    class Listener {
    }

    fun bindMovie(movie: MovieWithDetails)
}