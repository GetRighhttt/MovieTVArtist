package com.example.movietvartist.presentation.di.core.data

import com.example.movietvartist.data.api.APIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @Module means we're using dependency injection and it holds all the functions grouped
 * together with @Provides.
 *
 * @Singleton returns a single instance. It's very efficient.
 *
 * This module provides two dependencies.
 */
@Module
class NetworkModule(private val baseUrl: String) {

    /**
     * Let's build a retrofit instance and set it to GSON.
     */
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    /**
     * Now we need another method to provide an instance of our APIService in a return
     * statement.
     */
    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}