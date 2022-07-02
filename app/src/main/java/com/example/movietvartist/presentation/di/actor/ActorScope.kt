package com.example.movietvartist.presentation.di.actor

import javax.inject.Scope

/**
 * Essentially just need a scope class so the data doesn't persist
 * beyond the necessary time.
 *
 * Class doesn't need a class body.
 */

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ActorScope()