package com.example.movietvartist.presentation.di.actor

import com.example.movietvartist.presentation.actor.ActorActivity
import dagger.Subcomponent
import javax.inject.Scope

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
@ActorScope
@Subcomponent(modules = [ActorModule::class])
interface ActorSubComponent {

    fun inject(actorActivity: ActorActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create(): ActorSubComponent
    }
}