package com.example.trendingmovies.screens.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.trendingmovies.Constants
import com.example.trendingmovies.R
import com.example.trendingmovies.movies.MovieWithDetails
import com.example.trendingmovies.screens.common.mvcviews.BaseViewMvc
import kotlinx.android.synthetic.main.activity_movie_details.view.*

class MovieDetailsViewMvcImpl (inflater: LayoutInflater, container: ViewGroup?):
    BaseViewMvc<MovieDetailsViewMvc.Listener>(), MovieDetailsViewMvc{

    init {
        setRootView(inflater.inflate(R.layout.activity_movie_details, container, false))
    }

    override fun bindMovie(movie: MovieWithDetails) {
        val rootView = getRootView()

        if(movie.title.isNotEmpty()){
            rootView.title_info.text = movie.title
        } else {
            rootView.title_info.text = getContext().getString(R.string.no_title)
        }

        rootView.rating_info.text = movie.vote_average.toString()
        rootView.synopsis_info.text = movie.overview

        if(movie.releaseDate.isNotEmpty()){
            val releaseYear = movie.releaseDate.substring(0, 4)
            val text = "<small><font color='#808080'>($releaseYear)</font></small>"
            rootView.title_info.append(" ")
            rootView.title_info.append(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
            rootView.release_date_info.text = movie.releaseDate
        } else {
            rootView.release_date_info.text = getContext().getString(R.string.no_date)
        }

        if(!movie.thumbnail.isNullOrEmpty()){
            Glide.with(getContext())
                .load(Constants.IMAGE_BASE_URL + movie.thumbnail)
                .apply(
                    RequestOptions()
                        .centerCrop())
                .into(rootView.poster_detail)
        }

        if(!movie.backdrop.isNullOrEmpty()){
            Glide.with(getContext())
                .load(Constants.IMAGE_BASE_URL + movie.backdrop)

                .into(rootView.movie_detail_image)
        }
    }
}