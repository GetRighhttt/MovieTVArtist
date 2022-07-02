package com.example.movietvartist.presentation.di.core.data

import com.example.movietvartist.presentation.di.actor.ActorSubComponent
import com.example.movietvartist.presentation.di.movie.MovieSubComponent
import com.example.movietvartist.presentation.di.tvshow.TvShowSubComponent

/**
 * Interface to define functions to get the subcomponents instances.
 */
interface Injector {

    fun createMovieSubComponent(): MovieSubComponent
    fun createTvShowSubComponent(): TvShowSubComponent
    fun createActorSubComponent(): ActorSubComponent
}