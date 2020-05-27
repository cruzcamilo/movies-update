package com.example.trendingmovies.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.trendingmovies.MyApplication
import com.example.trendingmovies.common.dependencyinjection.CompositionRoot

open class BaseActivity: AppCompatActivity() {

    protected fun getCompositionRoot(): CompositionRoot{
        return (application as MyApplication).getCompositionRoot()
    }
}