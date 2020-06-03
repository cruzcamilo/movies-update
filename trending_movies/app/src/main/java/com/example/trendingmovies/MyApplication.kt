package com.example.trendingmovies

import android.app.Application
import com.example.trendingmovies.common.dependencyinjection.application.ApplicationComponent
import com.example.trendingmovies.common.dependencyinjection.application.DaggerApplicationComponent

class MyApplication: Application() {

    private lateinit var mApplicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder()
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent{
        return mApplicationComponent
    }
}