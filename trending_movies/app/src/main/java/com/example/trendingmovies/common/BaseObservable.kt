package com.example.trendingmovies.common

import java.util.*
import java.util.concurrent.ConcurrentHashMap

abstract class BaseObservable<LISTENER_CLASS> {

    private val mListeners =
        Collections.newSetFromMap(
            ConcurrentHashMap<LISTENER_CLASS, Boolean>(1)
        )


    final fun registerListener(listener: LISTENER_CLASS){
        mListeners.add(listener)
    }

    final fun unRegisterListener(listener: LISTENER_CLASS){
        mListeners.remove(listener)
    }

    fun getListeners(): MutableSet<LISTENER_CLASS> {
        return Collections.unmodifiableSet(mListeners)
    }

}