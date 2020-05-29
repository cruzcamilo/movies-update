package com.example.trendingmovies.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.trendingmovies.MyApplication
import com.example.trendingmovies.common.dependencyinjection.CompositionRoot
import com.example.trendingmovies.common.dependencyinjection.PresentationCompositionRoot

open class BaseActivity: AppCompatActivity() {

    var mPresentationCompositionRoot: PresentationCompositionRoot? = null

    protected fun getCompositionRoot():PresentationCompositionRoot{
        if(mPresentationCompositionRoot == null){
            mPresentationCompositionRoot = PresentationCompositionRoot(
                getAppCompositionRoot(),
                supportFragmentManager
            )
            return mPresentationCompositionRoot!!
        }
        return mPresentationCompositionRoot!!
    }

    protected fun getAppCompositionRoot(): CompositionRoot{
        return (application as MyApplication).getCompositionRoot()
    }
}