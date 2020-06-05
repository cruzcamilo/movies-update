package com.example.trendingmovies.common.dependencyinjection

import android.app.Service
import com.example.trendingmovies.MyApplication
import com.example.trendingmovies.common.dependencyinjection.application.ApplicationComponent
import com.example.trendingmovies.common.dependencyinjection.service.ServiceComponent
import com.example.trendingmovies.common.dependencyinjection.service.ServiceModule

abstract class BaseService: Service() {

    private var mIsServiceComponentUsed = false

    fun getServiceComponent(): ServiceComponent {
        if(mIsServiceComponentUsed){
            throw RuntimeException("There is no need to use injector more than use")
        }
        mIsServiceComponentUsed = true
        return getApplicationComponent().newServiceComponent(ServiceModule(this))
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (application as MyApplication).getApplicationComponent()
    }
}