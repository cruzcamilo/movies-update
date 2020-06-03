package com.example.trendingmovies.common.dependencyinjection

import com.example.trendingmovies.common.dependencyinjection.presentation.PresentationComponent
import com.example.trendingmovies.movies.FetchMovieDetailsUseCase
import com.example.trendingmovies.movies.FetchMoviesListUseCase
import com.example.trendingmovies.screens.common.dialogs.DialogsManager
import com.example.trendingmovies.screens.common.mvcviews.ViewMvcFactory
import java.lang.reflect.Field

class Injector(private val mPresentationComponent: PresentationComponent) {

    fun inject(client: Any){
        val clazz: Class<*> = client.javaClass
        val fields = clazz.declaredFields

        for (field in fields){
            if(isAnnotatedForInjection(field)){
                injectField(client, field)
            }
        }
    }

    private fun isAnnotatedForInjection (field: Field): Boolean{
        val annotations = field.declaredAnnotations

        for (annotation in annotations) {
            if (annotation is Service) {
                return true
            }
        }

        return false
    }

    private fun injectField(client: Any, field: Field?) {
        try {
            val isAccessibleInitially = field!!.isAccessible
            field.isAccessible = true
            field[client] = getServiceForClass(field.type)
            field.isAccessible = isAccessibleInitially
        } catch (e: IllegalAccessException) {
            throw java.lang.RuntimeException(e)
        }
    }

    private fun getServiceForClass(type: Class<*>): Any? {
        return when (type) {
            DialogsManager::class.java -> {
                mPresentationComponent.getDialogsManager()
            }
            ViewMvcFactory::class.java -> {
                mPresentationComponent.getViewMvcFactory()
            }
            FetchMoviesListUseCase::class.java -> {
                mPresentationComponent.getFetchMoviesListUseCase()
            }
            FetchMovieDetailsUseCase::class.java -> {
                mPresentationComponent.getFetchMoviesDetailsUseCase()
            }
            else -> {
                throw java.lang.RuntimeException("unsupported service type class: $type")
            }
        }
    }
}