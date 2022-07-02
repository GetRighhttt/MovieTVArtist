package com.example.movietvartist.presentation.di.core.data.app

import android.content.Context
import com.example.movietvartist.presentation.di.actor.ActorSubComponent
import com.example.movietvartist.presentation.di.movie.MovieSubComponent
import com.example.movietvartist.presentation.di.tvshow.TvShowSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Application Context module for dependency injection.
 * I believe only dagger still does this.
 *
 * We also need to list all the subcomponents of the module here.
 */

@Module(
    subcomponents = [MovieSubComponent::class,
        TvShowSubComponent::class,
        ActorSubComponent::class]
)
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }

}