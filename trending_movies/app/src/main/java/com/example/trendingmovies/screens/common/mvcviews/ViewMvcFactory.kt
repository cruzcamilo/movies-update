package com.example.trendingmovies.screens.common.mvcviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.example.trendingmovies.screens.common.ImageLoader
import com.example.trendingmovies.screens.moviedetails.MovieDetailsViewMvc
import com.example.trendingmovies.screens.moviedetails.MovieDetailsViewMvcImpl
import com.example.trendingmovies.screens.movielist.MovieListViewMvc
import com.example.trendingmovies.screens.movielist.MovieListViewMvcImpl

class ViewMvcFactory(
    val layoutInflater: LayoutInflater,
    val imageLoader: ImageLoader
) {

    fun <T : ViewMvc?> newInstance(mvcViewClass: Any, @Nullable container: ViewGroup?): T {
        val viewMvc: ViewMvc
        if (mvcViewClass == MovieListViewMvc::class) {
            viewMvc = MovieListViewMvcImpl(layoutInflater, container, imageLoader)
        } else if (mvcViewClass == MovieDetailsViewMvc::class) {
            viewMvc = MovieDetailsViewMvcImpl(layoutInflater, container, imageLoader)
        } else {
            throw IllegalArgumentException("unsupported MVC view class $mvcViewClass")
        }
        return viewMvc as T
    }
}