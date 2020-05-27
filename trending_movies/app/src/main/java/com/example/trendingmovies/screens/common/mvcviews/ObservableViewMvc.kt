package com.example.trendingmovies.screens.common.mvcviews

interface ObservableViewMvc<ListenerType>: ViewMvc {
    fun registerListener(listener: ListenerType)
    fun unRegisterListener(listener: ListenerType)
}