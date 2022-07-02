package com.example.movietvartist.presentation.di.core.data

import com.example.movietvartist.data.database.dao.ActorDao
import com.example.movietvartist.data.database.dao.MovieDao
import com.example.movietvartist.data.database.dao.TvShowDao
import com.example.movietvartist.data.repositoryimpl.local.actor.ActorLocalDataSource
import com.example.movietvartist.data.repositoryimpl.local.actor.ActorLocalDataSourceImpl
import com.example.movietvartist.data.repositoryimpl.local.movie.MovieLocalDataSource
import com.example.movietvartist.data.repositoryimpl.local.movie.MovieLocalDataSourceImpl
import com.example.movietvartist.data.repositoryimpl.local.tvshow.TvShowLocalDataSource
import com.example.movietvartist.data.repositoryimpl.local.tvshow.TvShowLocalDataSourceImpl
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
class LocalModule {
    /**
     * Now we need to create methods to provide our DATA SOURCES in our repository
     * layer with their dependencies.
     *
     * We are going to return however, an instance of their IMPLEMENTATION (IMPL).
     *
     * We have 3 data sources so we need a DI class for each one.
     *
     * And in this layer, the LOCAL data source has a DAO as a parameter for each
     * individual one.
     */

    @Singleton
    @Provides
    fun provideLocalMovieDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun provideLocalTvShowDataSource(tvShowDao: TvShowDao): TvShowLocalDataSource {
        return TvShowLocalDataSourceImpl(tvShowDao)
    }

    @Singleton
    @Provides
    fun provideLocalActorDataSource(actorDao: ActorDao): ActorLocalDataSource {
        return ActorLocalDataSourceImpl(actorDao)
    }

}