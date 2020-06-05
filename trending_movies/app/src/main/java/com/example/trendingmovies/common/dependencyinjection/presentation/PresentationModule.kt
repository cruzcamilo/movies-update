package com.example.trendingmovies.common.dependencyinjection.presentation

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.trendingmovies.movies.FetchMovieDetailsUseCase
import com.example.trendingmovies.networking.MovieDbApi
import com.example.trendingmovies.screens.common.ImageLoader
import com.example.trendingmovies.screens.common.dialogs.DialogsManager
import com.example.trendingmovies.screens.common.mvcviews.ViewMvcFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(val activity: FragmentActivity) {

    @Provides
    fun getDialogsManager(): DialogsManager {
        return DialogsManager(getFragmentManager())
    }

    @Provides
    fun getFragmentManager(): FragmentManager {return activity.supportFragmentManager}

    @Provides
    fun getLayoutInflater(): LayoutInflater {return LayoutInflater.from(activity)}

    @Provides
    fun getActivity(): Activity {return activity}

    @Provides fun context(activity: Activity): Context {return activity}

    @Provides
    fun getViewMvcFactory(layoutInflater: LayoutInflater, imageLoader: ImageLoader): ViewMvcFactory {
        return ViewMvcFactory(layoutInflater, imageLoader)
    }

    @Provides
    fun getImageLoader(activity: Activity): ImageLoader {return ImageLoader(activity)
    }

    @Provides
    fun getFetchMovieDetailsUseCase(movieDbApi: MovieDbApi): FetchMovieDetailsUseCase {
        return FetchMovieDetailsUseCase(movieDbApi)
    }

}
