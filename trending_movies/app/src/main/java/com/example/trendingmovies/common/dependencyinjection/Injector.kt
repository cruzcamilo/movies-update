package com.example.trendingmovies.common.dependencyinjection

import com.example.trendingmovies.screens.moviedetails.MovieDetailsActivity
import com.example.trendingmovies.screens.movielist.MovieListActivity

class Injector(private val mPresentationCompositionRoot: PresentationCompositionRoot) {

    fun inject(client: Any){
        when (client) {
            is MovieListActivity -> {
                injectMovieListActivity(client)
            }
            is MovieDetailsActivity -> {
                injectMovieDetailsActivity(client)
            }
            else -> {
                throw RuntimeException("invalid client: $client")
            }
        }
    }

    private fun injectMovieDetailsActivity(client: MovieDetailsActivity) {
        client.mViewMvcFactory = mPresentationCompositionRoot.getViewMvcFactory()
        client.mDialogsManager = mPresentationCompositionRoot.getDialogsManager()
        client.mFetchMovieDetailsUseCaseUseCase = mPresentationCompositionRoot.getFetchMovieDetailsUseCase()
    }

    private fun injectMovieListActivity(client: MovieListActivity) {
        client.mViewMvcFactory = mPresentationCompositionRoot.getViewMvcFactory()
        client.mDialogsManager = mPresentationCompositionRoot.getDialogsManager()
        client.mFetchMoviesListUseCase = mPresentationCompositionRoot.getFetchMovieListUseCase()
    }
}