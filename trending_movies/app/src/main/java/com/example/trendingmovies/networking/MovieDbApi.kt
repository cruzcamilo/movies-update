package com.example.trendingmovies.networking

import com.example.trendingmovies.movies.MovieWithDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbApi {

    @GET("3/movie/popular")
    fun popularMoviesList(@Query("page") page: Int): Call<MovieListResponseSchema>

    @GET("3/movie/{id}")
    fun movieDetails(@Path("id") movieId: Int): Call<MovieWithDetails>

    @GET("3/movie/{id}/images")
    fun moviePoster(@Path("id") movieId: Int): Call<PosterListResponseSchema>
}