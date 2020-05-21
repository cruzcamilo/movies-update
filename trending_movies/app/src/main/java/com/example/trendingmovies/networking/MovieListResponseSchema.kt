package com.example.trendingmovies.networking

import com.example.trendingmovies.movies.Movie
import com.google.gson.annotations.SerializedName

class MovieListResponseSchema(movies: List<Movie>){

    @SerializedName("results")
    private val mMovies = movies

    fun getMovies(): List<Movie> {
        return mMovies
    }
}