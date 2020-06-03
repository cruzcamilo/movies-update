package com.example.trendingmovies.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.trendingmovies.MyApplication
import com.example.trendingmovies.common.dependencyinjection.application.ApplicationComponent
import com.example.trendingmovies.common.dependencyinjection.presentation.PresentationComponent
import com.example.trendingmovies.common.dependencyinjection.presentation.PresentationModule

open class BaseActivity: AppCompatActivity() {

    private var mIsInjectionUsed = false

    fun getPresentationComponent(): PresentationComponent{
        if(mIsInjectionUsed){
            throw RuntimeException("There is no need to use injector more than use")
        }
        mIsInjectionUsed = true
        return getApplicationComponent().newPresentationComponent(PresentationModule(this))
    }

    private fun getApplicationComponent(): ApplicationComponent{
        return (application as MyApplication).getApplicationComponent()
    }
}