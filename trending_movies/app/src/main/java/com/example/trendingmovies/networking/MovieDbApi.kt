package com.example.trendingmovies.networking

import com.example.trendingmovies.movies.MovieWithDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbApi {

    @GET("3/movie/popular")
    fun popularMoviesList(): Call<MovieListResponseSchema>

    @GET("3/movie/{id}")
    fun movieDetails(@Path("id") movieId: Int): Call<MovieWithDetails>
}