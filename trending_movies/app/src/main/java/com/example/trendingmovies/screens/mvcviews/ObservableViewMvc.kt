package com.example.trendingmovies.screens.mvcviews

interface ObservableViewMvc<ListenerType>: ViewMvc {
    fun registerListener(listener: ListenerType)
    fun unRegisterListener(listener: ListenerType)
}