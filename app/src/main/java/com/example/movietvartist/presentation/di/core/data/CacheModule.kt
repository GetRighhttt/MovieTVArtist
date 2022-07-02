package com.example.movietvartist.presentation.di.core.data

import com.example.movietvartist.data.repositoryimpl.cache.actor.ActorCacheDataSource
import com.example.movietvartist.data.repositoryimpl.cache.actor.ActorCacheImpl
import com.example.movietvartist.data.repositoryimpl.cache.movie.MovieCacheDataSource
import com.example.movietvartist.data.repositoryimpl.cache.movie.MovieCacheImpl
import com.example.movietvartist.data.repositoryimpl.cache.tvshow.TvShowCacheDataSource
import com.example.movietvartist.data.repositoryimpl.cache.tvshow.TvShowCacheImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Now we need to create methods to provide our DATA SOURCES in our repository
 * layer with their dependencies.
 *
 * We have 3 data sources so we need a DI class for each one.
 */

@Module
class CacheModule {

    /**
     * Now we need to create methods to provide our DATA SOURCES in our repository
     * layer with their dependencies.
     *
     * We are going to return however, an instance of their IMPLEMENTATION (IMPL).
     *
     * We have 3 data sources so we need a DI class for each one.
     *
     * Because our cache data source doesn't have any parameters or constructors,
     * dependency injection with this module is a lot easier.
     *
     */

    @Singleton
    @Provides
    fun provideMovieCacheDataSource(): MovieCacheDataSource {
        return MovieCacheImpl()
    }

    @Singleton
    @Provides
    fun provideTvShowCacheDataSource(): TvShowCacheDataSource {
        return TvShowCacheImpl()
    }

    @Singleton
    @Provides
    fun provideActorCacheDataSource(): ActorCacheDataSource {
        return ActorCacheImpl()
    }


}