package com.example.movietvartist.presentation.di.core.data.domain

import com.example.movietvartist.domain.repository.ActorRepository
import com.example.movietvartist.domain.repository.MovieRepository
import com.example.movietvartist.domain.repository.TvShowRepository
import com.example.movietvartist.domain.updateusecase.UpdateActorUseCase
import com.example.movietvartist.domain.updateusecase.UpdateMoviesUseCase
import com.example.movietvartist.domain.updateusecase.UpdateTvShowUseCase
import com.example.movietvartist.domain.usecase.ActorUseCase
import com.example.movietvartist.domain.usecase.MovieUseCase
import com.example.movietvartist.domain.usecase.TvShowUseCase
import dagger.Module
import dagger.Provides

/**
 * Our use cases have dependencies also so we have to provide dependencies for
 * those as well.
 *
 * Essentially allowing our use cases or other aspects of the program to be used as
 * dependencies.
 *
 * We pass in our repositories because that's what the use cases are using.
 */

@Module
class UseCaseModule {

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): MovieUseCase {
        return MovieUseCase(movieRepository)
    }

    @Provides
    fun provideUpdateMovieUseCase(movieRepository: MovieRepository): UpdateMoviesUseCase {
        return UpdateMoviesUseCase(movieRepository)
    }

    @Provides
    fun providesTvShowUseCase(tvShowRepository: TvShowRepository): TvShowUseCase {
        return TvShowUseCase(tvShowRepository)
    }

    @Provides
    fun providesUpdateTvShowUseCase(tvShowRepository: TvShowRepository): UpdateTvShowUseCase {
        return UpdateTvShowUseCase(tvShowRepository)
    }

    @Provides
    fun provideActorUseCase(actorRepository: ActorRepository): ActorUseCase {
        return ActorUseCase(actorRepository)
    }

    @Provides
    fun provideUpdateActorUseCase(actorRepository: ActorRepository): UpdateActorUseCase {
        return UpdateActorUseCase(actorRepository)
    }


}