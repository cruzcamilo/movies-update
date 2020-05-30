package com.example.trendingmovies.screens.common.activities

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.trendingmovies.MyApplication
import com.example.trendingmovies.common.dependencyinjection.CompositionRoot
import com.example.trendingmovies.common.dependencyinjection.PresentationCompositionRoot

open class BaseActivity: AppCompatActivity() {

    private var mPresentationCompositionRoot: PresentationCompositionRoot? = null

    protected fun getCompositionRoot():PresentationCompositionRoot{
        if(mPresentationCompositionRoot == null){
            mPresentationCompositionRoot = PresentationCompositionRoot(
                getAppCompositionRoot(),
                this
            )
        }

        return mPresentationCompositionRoot!!
    }

    private fun getAppCompositionRoot(): CompositionRoot{
        return (application as MyApplication).getCompositionRoot()
    }
}