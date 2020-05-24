package com.example.trendingmovies.movies

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("title") var title: String,
    @SerializedName("poster_path")var thumbnail: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("vote_average") var vote_average: Double,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("id") var id: String) {

    override fun toString(): String {
        return "Movie{" +
                "title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", overview='" + overview + '\'' +
                ", vote_average=" + vote_average +
                ", releaseDate='" + releaseDate + '\'' +
                '}'
    }
}