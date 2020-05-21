package com.example.trendingmovies.networking

import retrofit2.Call
import retrofit2.http.GET

interface MovieDbApi {

    @GET("/popular?")
    fun popularMoviesList(): Call<MovieListResponseSchema>

}