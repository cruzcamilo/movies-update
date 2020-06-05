package com.example.trendingmovies

import android.app.Application
import com.example.trendingmovies.common.dependencyinjection.application.ApplicationComponent
import com.example.trendingmovies.common.dependencyinjection.application.ApplicationModule
import com.example.trendingmovies.common.dependencyinjection.application.DaggerApplicationComponent

class MyApplication: Application() {

    private lateinit var mApplicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent{
        return mApplicationComponent
    }
}