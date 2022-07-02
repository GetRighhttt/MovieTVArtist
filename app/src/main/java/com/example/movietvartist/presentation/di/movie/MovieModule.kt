package com.example.movietvartist.presentation.di.movie

import com.example.movietvartist.domain.updateusecase.UpdateMoviesUseCase
import com.example.movietvartist.domain.usecase.MovieUseCase
import com.example.movietvartist.presentation.di.actor.ActorScope
import com.example.movietvartist.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dependency for our view model factory class.
 *
 * We also use a scope so the instance of the view model doesn't persist in memory even
 * after the flow has finished. So we want to limit the scope essentially.
 *
 * That's why we have to create a scope class, then annotate it with the necessary class
 * annotation.
 */
@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMovieUseCase: MovieUseCase,
        updateMovieUseCase: UpdateMoviesUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(getMovieUseCase, updateMovieUseCase)
    }
}