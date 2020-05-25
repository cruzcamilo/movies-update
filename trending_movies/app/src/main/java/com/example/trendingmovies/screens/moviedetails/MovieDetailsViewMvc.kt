package com.example.trendingmovies.screens.moviedetails

import com.example.trendingmovies.movies.MovieWithDetails
import com.example.trendingmovies.screens.mvcviews.ObservableViewMvc

interface MovieDetailsViewMvc: ObservableViewMvc<MovieDetailsViewMvc.Listener> {

    class Listener {
        //Currently there's no implementation
    }

    fun bindMovie(movie: MovieWithDetails)
}