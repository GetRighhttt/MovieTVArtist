package com.example.movietvartist.presentation.di.actor

import com.example.movietvartist.domain.updateusecase.UpdateActorUseCase
import com.example.movietvartist.domain.usecase.ActorUseCase
import com.example.movietvartist.presentation.actor.ActorViewModelFactory
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
class ActorModule {

    @ActorScope
    @Provides
    fun provideActorViewModelFactory(
        getActorUseCase: ActorUseCase,
        updateActorUseCase: UpdateActorUseCase
    ): ActorViewModelFactory {
        return ActorViewModelFactory(getActorUseCase, updateActorUseCase)
    }
}