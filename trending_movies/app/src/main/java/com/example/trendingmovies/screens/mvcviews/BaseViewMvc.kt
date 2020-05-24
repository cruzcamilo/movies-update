package com.example.trendingmovies.screens.mvcviews

import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import com.example.trendingmovies.common.BaseObservable

abstract class BaseViewMvc<ListenerType>: BaseObservable<ListenerType>(),
    ObservableViewMvc<ListenerType> {

    private lateinit var mRootView:View

    fun setRootView(view: View) {
        mRootView = view
    }

    override fun getRootView(): View {
        return mRootView
    }

    protected fun getContext(): Context {
        return getRootView().context
    }

    protected open fun <T : View> findViewById(@IdRes id: Int): T {
        return mRootView.findViewById(id)
    }

}