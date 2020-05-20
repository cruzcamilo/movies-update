package com.example.trendingmovies.screens.mvcviews

import android.content.Context
import android.view.View

abstract class BaseViewMvc<ListenerType>: ObservableViewMvc<ListenerType> {

    lateinit var mRootview:View

    override fun getRootView(): View {
        return mRootview
    }

    protected fun getContext(): Context {
        return getRootView().context
    }

}