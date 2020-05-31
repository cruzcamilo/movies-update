package com.example.trendingmovies.screens.common.activities

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.trendingmovies.MyApplication
import com.example.trendingmovies.common.dependencyinjection.CompositionRoot
import com.example.trendingmovies.common.dependencyinjection.Injector
import com.example.trendingmovies.common.dependencyinjection.PresentationCompositionRoot
import java.lang.RuntimeException

open class BaseActivity: AppCompatActivity() {

    private var mPresentationCompositionRoot: PresentationCompositionRoot? = null
    private var mIsInjectionUsed = false

    fun getInjector(): Injector{
        if(mIsInjectionUsed){
            throw RuntimeException("There is no need to use injector more than use")
        }
        mIsInjectionUsed = true
        return Injector(getCompositionRoot())
    }

    private fun getCompositionRoot():PresentationCompositionRoot{
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