package com.example.trendingmovies.common.dependencyinjection.application

import com.example.trendingmovies.common.dependencyinjection.presentation.NetworkingModule
import com.example.trendingmovies.common.dependencyinjection.presentation.PresentationComponent
import com.example.trendingmovies.common.dependencyinjection.presentation.PresentationModule
import com.example.trendingmovies.common.dependencyinjection.service.ServiceComponent
import com.example.trendingmovies.common.dependencyinjection.service.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkingModule::class])
interface ApplicationComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
    fun newServiceComponent(serviceModule: ServiceModule): ServiceComponent
}