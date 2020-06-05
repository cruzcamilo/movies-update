package com.example.trendingmovies.screens.common.dialogs

import androidx.fragment.app.DialogFragment
import com.example.trendingmovies.MyApplication
import com.example.trendingmovies.common.dependencyinjection.application.ApplicationComponent
import com.example.trendingmovies.common.dependencyinjection.presentation.PresentationComponent
import com.example.trendingmovies.common.dependencyinjection.presentation.PresentationModule

class BaseDialog : DialogFragment() {
    private var mIsInjectionUsed = false

    fun getPresentationComponent(): PresentationComponent {
        if(mIsInjectionUsed){
            throw RuntimeException("There is no need to use injector more than use")
        }
        mIsInjectionUsed = true
        return getApplicationComponent().newPresentationComponent(PresentationModule(activity!!))
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (activity?.application as MyApplication).getApplicationComponent()
    }
}