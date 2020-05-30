package com.example.trendingmovies.common.dependencyinjection

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.trendingmovies.movies.FetchMovieDetailsUseCase
import com.example.trendingmovies.movies.FetchMoviesListUseCase
import com.example.trendingmovies.screens.common.ImageLoader
import com.example.trendingmovies.screens.common.dialogs.DialogsManager
import com.example.trendingmovies.screens.common.mvcviews.ViewMvcFactory

class PresentationCompositionRoot(
    val compositionRoot: CompositionRoot,
    val activity: AppCompatActivity
) {

    fun getFragmentManager(): FragmentManager{return activity.supportFragmentManager}

    fun getLayoutInflater(): LayoutInflater{return LayoutInflater.from(activity)}

    fun getDialogsManager(): DialogsManager {
        return DialogsManager(getFragmentManager())
    }

    fun getFetchMovieDetailsUseCase(): FetchMovieDetailsUseCase {
        return compositionRoot.getFetchMovieDetailsUseCase()
    }

    fun getFetchMovieListUseCase(): FetchMoviesListUseCase {
        return compositionRoot.getFetchMovieListUseCase()
    }

    fun getViewMvcFactory(): ViewMvcFactory{
        return ViewMvcFactory(getLayoutInflater(), getImageLoader())
    }

    fun getImageLoader(): ImageLoader{return ImageLoader(activity)}
}