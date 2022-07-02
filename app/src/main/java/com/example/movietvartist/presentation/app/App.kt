package com.example.movietvartist.presentation.app

import android.app.Application
import com.example.movietvartist.BuildConfig
import com.example.movietvartist.presentation.di.actor.ActorSubComponent
import com.example.movietvartist.presentation.di.core.data.Injector
import com.example.movietvartist.presentation.di.core.data.NetworkModule
import com.example.movietvartist.presentation.di.core.data.RemoteDataModule
import com.example.movietvartist.presentation.di.core.data.app.AppComponent
import com.example.movietvartist.presentation.di.core.data.app.AppModule
import com.example.movietvartist.presentation.di.core.data.app.DaggerAppComponent
import com.example.movietvartist.presentation.di.movie.MovieSubComponent
import com.example.movietvartist.presentation.di.tvshow.TvShowSubComponent
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerApplication

/**
 * This class will serve as the class to implement injector methods we defined in
 * our injector interface.
 *
 * This is really only used when working with Dagger. Not HILT, or KOIN.
 */
class App: Application(), Injector {

    /*
    Construct a private reference variable for the app component interface.
    We are going to construct app component inside onCreate() so we have to override
    that method.
     */
    private lateinit var appComponent: AppComponent

    /**
     * Here is where we build our dagger component.
     */
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()
    }

    /**
     * Here is where we create each individual subcomponent.
     */

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

    override fun createTvShowSubComponent(): TvShowSubComponent {
        return appComponent.tvShowSubComponent().create()
    }

    override fun createActorSubComponent(): ActorSubComponent {
        return appComponent.actorSubComponent().create()
    }
}