package com.example.trendingmovies.screens.common

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ImageLoader (val activity: Activity) {


    private var mDefaultRequestOptions: RequestOptions? = null

    private val movieListRequestOptions: RequestOptions = RequestOptions()
        .override(540, 840)
        .centerCrop()

    private val mCoverImageOptions: RequestOptions = RequestOptions()
        .override(406, 228)

    fun loadImage(uri: String, options: RequestOptions, target: ImageView){
        Glide.with(activity).load(uri).apply(options).into(target)
    }

    fun getDefaultOptions(): RequestOptions{
        if(mDefaultRequestOptions == null){
            mDefaultRequestOptions = RequestOptions().centerCrop()
        }

        return mDefaultRequestOptions!!
    }

    fun getMoviePosterOptions(): RequestOptions{
        return movieListRequestOptions
    }

    fun getMovieCoverImageOptions(): RequestOptions{
        return mCoverImageOptions
    }
}