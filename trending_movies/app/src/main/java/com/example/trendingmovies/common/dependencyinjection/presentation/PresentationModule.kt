package com.example.trendingmovies.common.dependencyinjection.presentation

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
    fun getViewMvcFactory(): ViewMvcFactory {
        return ViewMvcFactory(getLayoutInflater(), getImageLoader())
    }

    @Provides
    fun getImageLoader(): ImageLoader {return ImageLoader(activity)
    }

    @Provides
    fun getFetchMovieDetailsUseCase(movieDbApi: MovieDbApi): FetchMovieDetailsUseCase {
        return FetchMovieDetailsUseCase(movieDbApi)
    }

}
