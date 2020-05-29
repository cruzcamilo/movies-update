package com.example.trendingmovies.common.dependencyinjection

import androidx.fragment.app.FragmentManager
import com.example.trendingmovies.movies.FetchMovieDetailsUseCase
import com.example.trendingmovies.movies.FetchMoviesListUseCase
import com.example.trendingmovies.screens.common.dialogs.DialogsManager

class PresentationCompositionRoot(
    val compositionRoot: CompositionRoot,
    val supportFragmentManager: FragmentManager
) {

    fun getDialogsManager(): DialogsManager {
        return DialogsManager(supportFragmentManager)
    }

    fun getFetchMovieDetailsUseCase(): FetchMovieDetailsUseCase {
        return compositionRoot.getFetchMovieDetailsUseCase()
    }

    fun getFetchMovieListUseCase(): FetchMoviesListUseCase {
        return compositionRoot.getFetchMovieListUseCase()
    }
}