package com.example.trendingmovies.movies

import com.google.gson.annotations.SerializedName

data class Movie (@SerializedName("poster_path") var thumbnail: String,
                  @SerializedName("id") var id: String)