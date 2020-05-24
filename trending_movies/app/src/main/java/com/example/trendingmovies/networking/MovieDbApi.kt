package com.example.trendingmovies.networking

import retrofit2.Call
import retrofit2.http.GET

interface MovieDbApi {

    @GET("3/movie/popular")
    fun popularMoviesList(): Call<MovieListResponseSchema>

}