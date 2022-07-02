package com.example.movietvartist.presentation.di.core.data.domain

import com.example.movietvartist.data.repositoryimpl.ActorRepositoryImpl
import com.example.movietvartist.data.repositoryimpl.MovieRepositoryImpl
import com.example.movietvartist.data.repositoryimpl.TvShowRepositoryImpl
import com.example.movietvartist.data.repositoryimpl.cache.actor.ActorCacheDataSource
import com.example.movietvartist.data.repositoryimpl.cache.movie.MovieCacheDataSource
import com.example.movietvartist.data.repositoryimpl.cache.tvshow.TvShowCacheDataSource
import com.example.movietvartist.data.repositoryimpl.local.actor.ActorLocalDataSource
import com.example.movietvartist.data.repositoryimpl.local.movie.MovieLocalDataSource
import com.example.movietvartist.data.repositoryimpl.local.tvshow.TvShowLocalDataSource
import com.example.movietvartist.data.repositoryimpl.remote.actor.ActorRemoteDataSource
import com.example.movietvartist.data.repositoryimpl.remote.movie.MovieRemoteDataSource
import com.example.movietvartist.data.repositoryimpl.remote.tvshow.TvShowRemoteDataSource
import com.example.movietvartist.domain.repository.ActorRepository
import com.example.movietvartist.domain.repository.MovieRepository
import com.example.movietvartist.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Method to provide each level of our use cases with our repository
 */
@Module
class RepositoryModule {

    /**
     * Because our use cases classes has repository instances as dependencies, and those
     * repository instances REQUIRE instances of each data source, we have to put each data
     * source as a repository.
     *
     * And then we return an instance of the repository, use the IMPL to pass the data
     * sources as arguments.
     */
    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieRemoteDataSource, movieLocalDataSource, movieCacheDataSource)
    }

    @Singleton
    @Provides
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): TvShowRepository {
        return TvShowRepositoryImpl(
            tvShowRemoteDataSource, tvShowLocalDataSource, tvShowCacheDataSource)
    }

    @Singleton
    @Provides
    fun provideActorRepository(
        actorRemoteDataSource: ActorRemoteDataSource,
        actorLocalDataSource: ActorLocalDataSource,
        actorCacheDataSource: ActorCacheDataSource
    ): ActorRepository {
        return ActorRepositoryImpl(
            actorRemoteDataSource, actorLocalDataSource, actorCacheDataSource)
    }
}