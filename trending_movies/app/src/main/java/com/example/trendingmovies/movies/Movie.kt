package com.example.trendingmovies.movies

class Movie constructor(var title: String, var thumbnail: String, var overview: String,
                        var vote_average: Double, var releaseDate: String, var id: String) {

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