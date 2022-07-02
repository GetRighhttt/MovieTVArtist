package com.example.movietvartist.presentation.di.movie

import com.example.movietvartist.presentation.actor.ActorActivity
import com.example.movietvartist.presentation.di.actor.ActorModule
import com.example.movietvartist.presentation.di.actor.ActorSubComponent
import com.example.movietvartist.presentation.movie.MovieActivity
import dagger.Subcomponent

/**
 * Here we need to define the list of modules related. We will use this subcomponent
 * to inject dependencies to the activity.
 *
 * We need to define an inject method here to do so and keep an instance of the activity
 * as a parameter.
 *
 * We also have to define a subcomponent factory inside the component so
 * the App Component knows how to create instances of this component.
 *
 */
@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {

    fun inject(movieActivity: MovieActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create(): MovieSubComponent
    }
}