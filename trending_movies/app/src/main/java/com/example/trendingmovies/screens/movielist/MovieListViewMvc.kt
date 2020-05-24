package com.example.trendingmovies.screens.movielist

import com.example.trendingmovies.movies.Movie
import com.example.trendingmovies.screens.mvcviews.ObservableViewMvc

interface MovieListViewMvc: ObservableViewMvc<MovieListViewMvc.Listener> {

    interface Listener {
        fun onMovieClicked(movie: Movie)
    }

    fun bindMovies(movies: List<Movie>)
}