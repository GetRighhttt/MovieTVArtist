package com.example.movietvartist.presentation.di.core.data

import com.example.movietvartist.data.api.APIService
import com.example.movietvartist.data.repositoryimpl.remote.actor.ActorRemoteDataSource
import com.example.movietvartist.data.repositoryimpl.remote.actor.ActorRemoteDataSourceImpl
import com.example.movietvartist.data.repositoryimpl.remote.movie.MovieRemoteDataSource
import com.example.movietvartist.data.repositoryimpl.remote.movie.MovieRemoteDataSourceImpl
import com.example.movietvartist.data.repositoryimpl.remote.tvshow.TvShowRemoteDataSource
import com.example.movietvartist.data.repositoryimpl.remote.tvshow.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Now we need to create methods to provide our DATA SOURCES in our repository
 * layer with their dependencies.
 *
 * We are going to return however, an instance of their IMPLEMENTATION (IMPL).
 *
 * We have 3 data sources so we need a DI class for each one.
 *
 * We need an apiKey as a parameter because our remote data source calls for it.
 */

@Module
class RemoteDataModule(
    private val apiKey: String
) {

    /**
     * Method to provide movie with its dependencies.
     *
     * We have two parameters that we used in our repositoryIMPL layers for our remote data
     * source.
     */
    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(apiService: APIService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(apiService, apiKey)
    }


    @Singleton
    @Provides
    fun provideTvShowRemoteDataSource(apiService: APIService): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(apiService, apiKey)
    }

    @Singleton
    @Provides
    fun provideActorRemoteDataSource(apiService: APIService): ActorRemoteDataSource {
        return ActorRemoteDataSourceImpl(apiService, apiKey)
    }
}



