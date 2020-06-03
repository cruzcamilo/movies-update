package com.example.trendingmovies.common.dependencyinjection.presentation

import com.example.trendingmovies.Constants
import com.example.trendingmovies.networking.MovieDbApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkingModule {

    private lateinit var okHttpClient: OkHttpClient

    @Singleton
    @Provides
    fun getOkHttpClient(): OkHttpClient {
        okHttpClient = OkHttpClient()
        return okHttpClient.newBuilder().addInterceptor { chain ->
            val original = chain.request()
            val httpUrl = original.url()
            val newHttpUrl =
                httpUrl.newBuilder().addQueryParameter("api_key", Constants.API_KEY).build()
            val requestBuilder = original.newBuilder().url(newHttpUrl)
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()
    }

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getMovieDbApi(retrofit: Retrofit): MovieDbApi {
        return retrofit.create(MovieDbApi::class.java)
    }
}