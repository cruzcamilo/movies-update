package com.example.trendingmovies.networking

import com.example.trendingmovies.movies.Movie
import com.example.trendingmovies.movies.Poster
import com.google.gson.annotations.SerializedName

class PosterListResponseSchema (poster: List<Poster>) {

    @SerializedName("backdrops")
    private val mPosters = poster

    fun getPosters(): List<Poster> {
        return mPosters
    }

}
