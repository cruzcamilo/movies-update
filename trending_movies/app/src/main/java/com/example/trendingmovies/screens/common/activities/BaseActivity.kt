package com.example.trendingmovies.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.trendingmovies.MyApplication
import com.example.trendingmovies.common.dependencyinjection.CompositionRoot
import com.example.trendingmovies.common.dependencyinjection.Injector
import com.example.trendingmovies.common.dependencyinjection.PresentationCompositionRoot
import com.example.trendingmovies.common.dependencyinjection.application.ApplicationComponent

open class BaseActivity: AppCompatActivity() {

    private var mIsInjectionUsed = false

    fun getInjector(): Injector{
        if(mIsInjectionUsed){
            throw RuntimeException("There is no need to use injector more than use")
        }
        mIsInjectionUsed = true
        return Injector(getCompositionRoot())
    }

    private fun getCompositionRoot():PresentationCompositionRoot{
        return PresentationCompositionRoot(getApplicationComponent(), this)
    }

    private fun getApplicationComponent(): ApplicationComponent{
        return (application as MyApplication).getApplicationComponent()
    }
}