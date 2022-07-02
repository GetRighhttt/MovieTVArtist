package com.example.movietvartist.presentation.di.core.data.app

import com.example.movietvartist.presentation.di.actor.ActorSubComponent
import com.example.movietvartist.presentation.di.core.data.*
import com.example.movietvartist.presentation.di.core.data.domain.RepositoryModule
import com.example.movietvartist.presentation.di.core.data.domain.UseCaseModule
import com.example.movietvartist.presentation.di.movie.MovieSubComponent
import com.example.movietvartist.presentation.di.tvshow.TvShowSubComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Component class that is required for dagger. Essentially just list all the different
 * modules that we have created for the app to use for DI.
 *
 * We also have to define functions to get subcomponent factories.
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        RemoteDataModule::class,
        LocalModule::class,
        NetworkModule::class,
        CacheModule::class]
)
interface AppComponent {
    fun movieSubComponent(): MovieSubComponent.Factory
    fun tvShowSubComponent(): TvShowSubComponent.Factory
    fun actorSubComponent(): ActorSubComponent.Factory
}