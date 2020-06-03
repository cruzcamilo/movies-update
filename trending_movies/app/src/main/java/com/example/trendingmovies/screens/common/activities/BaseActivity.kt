package com.example.trendingmovies.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.trendingmovies.MyApplication
import com.example.trendingmovies.common.dependencyinjection.CompositionRoot
import com.example.trendingmovies.common.dependencyinjection.Injector
import com.example.trendingmovies.common.dependencyinjection.PresentationCompositionRoot
import com.example.trendingmovies.common.dependencyinjection.application.ApplicationComponent
import com.example.trendingmovies.common.dependencyinjection.presentation.DaggerPresentationComponent
import com.example.trendingmovies.common.dependencyinjection.presentation.PresentationComponent
import com.example.trendingmovies.common.dependencyinjection.presentation.PresentationModule

open class BaseActivity: AppCompatActivity() {

    private var mIsInjectionUsed = false

    fun getInjector(): Injector{
        if(mIsInjectionUsed){
            throw RuntimeException("There is no need to use injector more than use")
        }
        mIsInjectionUsed = true
        return Injector(getPresentationComponent())
    }

    private fun getPresentationComponent(): PresentationComponent {
        return DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule(this, getApplicationComponent()))
            .build()
    }

    private fun getApplicationComponent(): ApplicationComponent{
        return (application as MyApplication).getApplicationComponent()
    }
}